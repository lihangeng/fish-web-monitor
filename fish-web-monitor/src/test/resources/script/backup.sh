##############################################################################################################################
#                                            数据备份
##############################################################################################################################
#计算某日前若干天的日期
olddate ()
{
  strdate=$1
  days=$2
  yy=`echo $strdate|cut -c 1-4`
  mm=`echo $strdate|cut -c 5-6`
  dd=`echo $strdate|cut -c 7-8`
  sav_dd=$days
  days=`expr $days - $dd`
  while [ $days -ge 0 ]
  do
    mm=`expr $mm - 1`
    [ $mm -eq 0 ] && mm=12 && yy=`expr $yy - 1`
    aaa=`cal $mm $yy`
    bbb=`echo $aaa|awk '{print $NF}'`
    days=`expr $days - $bbb`
  done
  dd=`expr 0 - $days`
  expr $dd : "^.$" > /dev/null && dd=0$dd
  expr $mm : "^.$" > /dev/null && mm=0$mm
  echo $yy-$mm-$dd
}

gettime()
{
    gettime=`date '+%Y-%m-%d %H:%M:%S'`
    echo $gettime
}




#当前时间
WORKDATE=`date +%Y%m%d`

#进入临时目录
BKPATH=/home/atmv
ATMVPATH=/home/atmv
LOGPATH=/home/atmv/log

if [ ! -d $BKPATH ]
then
    mkdir -p $BKPATH
fi

cd $BKPATH
if [ $? -ne 0 ]
then
   echo `gettime` "没有进入指定的目录"  >>$LOGPATH/backup$WORKDATE.log
   exit 1
fi

echo "当前时间 ：$WORKDATE"

#连接数据库，取得需要备份的历史库表
hostIp=localhost
username=root
password=admin
database=atmv
while read Line
do
    echo `gettime` "==============================================================================================" >>$LOGPATH/backup$WORKDATE.log
    echo `gettime` $Line       >>$LOGPATH/backup$WORKDATE.log
    srctab=`echo "$Line"|awk -F"/" '{print $1}'`
    baktab=`echo "$Line"|awk -F"/" '{print $2}'`
    note=`echo "$Line"|awk -F"/" '{print $3}'`
    sql=`echo "$Line"|awk -F"/" '{print $4}'`
    day=`echo "$Line"|awk -F"/" '{print $5}'`
    backupDay=`echo "$Line"|awk -F"/" '{print $6}'`
    echo `gettime` $srctab $baktab $note $sql $day  >>$LOGPATH/backup$WORKDATE.log
    TWORKDATE=`olddate $WORKDATE $day`
    BACKUPWORKDATE = `olddate $WORKDATE $backupDay`
    echo `gettime` "开始备份的表?名:$srctab 备份的天数:$day  备份的日期:$TWORKDATE"  >>$LOGPATH/backup$WORKDATE.log
      datestart=`date +%s`
      echo `gettime` "select * from $srctab where $note <= '$TWORKDATE' $sql into outfile curtohis_$srctab$WORKDATE.txt"  >>$LOGPATH/backup$WORKDATE.log
      mysql -h $hostIp -u $username -p$password $database -e "select * from $srctab where $note <= '$TWORKDATE' $sql into outfile curtohis_$srctab$WORKDATE.txt"   >>$LOGPATH/backup$WORKDATE.log
      if [ $? -ne 0 ]
      then
        echo `gettime` "表 $srctab 导出数据异常!"   >>$LOGPATH/backup$WORKDATE.log
        continue
      fi
      dateend=`date +%s`
      echo `gettime` 导出表  $srctab 耗时 $(expr $dateend - $datestart) 秒  >>$LOGPATH/backup$WORKDATE.log

      datestart=`date +%s`
      echo `gettime` "load data local infile curtohis_$srctab$WORKDATE.txt into table $baktab" >>$LOGPATH/backup$WORKDATE.log
      mysql -h $hostIp -u $username -p$password $database -e "load data local infile curtohis_$srctab$WORKDATE.txt into table $baktab"  >>$LOGPATH/backup$WORKDATE.log
      if [ $? -ne 0 ]
      then
        echo `gettime` " 表 $baktab 导入数据异常!"  >>$LOGPATH/backup$WORKDATE.log
        continue
      fi
      dateend=`date +%s`
      echo `gettime` 导入表  $baktab 耗时 $(expr $dateend - $datestart) 秒  >>$LOGPATH/backup$WORKDATE.log

      echo `gettime` "select count(*) from $srctab where $note <= '$TWORKDATE' $sql" >>$LOGPATH/backup$WORKDATE.log
      return_value=$(mysql -h $hostIp -u $username -p$password $database -e "select count(*) from $srctab where $note <= '$TWORKDATE' $sql")
      number_value=`echo $return_value | awk '{print $3}'`
      echo `gettime` "当前表名和需要删除的记录数"  $srctab  $number_value    >>$LOGPATH/backup$WORKDATE.log
      datestart=`date +%s`
      echo `gettime` "delete  from ( select * from $srctab where $note <= '$TWORKDATE' $sql  )" >>$LOGPATH/backup$WORKDATE.log
      mysql -h $hostIp -u $username -p$password $database -e "delete  from ( select * from $srctab where $note <= '$TWORKDATE' $sql)"  >>$LOGPATH/backup$WORKDATE.log
      dateend=`date +%s`
      echo `gettime` 清理 表  $srctab 耗时 $(expr $dateend - $datestart) 秒  >>$LOGPATH/backup$WORKDATE.log
      
      echo `gettime` "开始备份的历史表?名:$baktab 备份的天数:$backupDay  备份的日期:$BACKUPWORKDATE"  >>$LOGPATH/backup$WORKDATE.log
      datestart=`date +%s`
      echo `gettime` "select * from $baktab where $note <= '$BACKUPWORKDATE' $sql into outfile backup_$baktab$BACKUPWORKDATE.txt"  >>$LOGPATH/backup$WORKDATE.log
      mysql -h $hostIp -u $username -p$password $database -e "select * from $baktab where $note <= '$BACKUPWORKDATE' $sql into outfile backup_$baktab$BACKUPWORKDATE.txt"   >>$LOGPATH/backup$WORKDATE.log
      if [ $? -ne 0 ]
      then
        echo `gettime` "历史表 $baktab 导出数据异常!"   >>$LOGPATH/backup$WORKDATE.log
        continue
      fi
      dateend=`date +%s`
      echo `gettime` 导出历史表  $baktab 耗时 $(expr $dateend - $datestart) 秒  >>$LOGPATH/backup$WORKDATE.log
      
      echo `gettime` "select count(*) from $baktab where $note <= '$BACKUPWORKDATE' $sql" >>$LOGPATH/backup$WORKDATE.log
      return_value=$(mysql -h $hostIp -u $username -p$password $database -e "select count(*) from $baktab where $note <= '$BACKUPWORKDATE' $sql")
      number_value=`echo $return_value | awk '{print $3}'`
      echo `gettime` "当前表名和需要删除的记录数"  $baktab  $number_value    >>$LOGPATH/backup$WORKDATE.log
      datestart=`date +%s`
      echo `gettime` "delete  from ( select * from $baktab where $note <= '$BACKUPWORKDATE' $sql  )" >>$LOGPATH/backup$WORKDATE.log
      mysql -h $hostIp -u $username -p$password $database -e "delete  from ( select * from $baktab where $note <= '$BACKUPWORKDATE' $sql)"  >>$LOGPATH/backup$WORKDATE.log
      dateend=`date +%s`
      echo `gettime` 清理 历史表  $baktab 耗时 $(expr $dateend - $datestart) 秒  >>$LOGPATH/backup$WORKDATE.log
      
    echo `gettime` "======================================================================================================================="  >>$LOGPATH/backup$WORKDATE.log
done < $ATMVPATH/cfgtable.txt
#删除所有从当前表导出的文件
rm -rf curtohis_*$WORKDATE.txt 
exit 0
