/**
 * 
 */
package com.yihuacomputer.fish.api.advert.util;

import com.yihuacomputer.fish.api.advert.AdvertType;

/**
 * 广告类型转化服务
 * 
 * @author xuxigang
 * 
 */
public class AdvertTypeConversionService {
    /**
     * 获得特定厂商的广告类型
     * 
     * @param type
     * @return
     */
    public static String convert(AdvertType type) {
        return AdvertiseType.get(type.getId()).name();
    }

    enum AdvertiseType {

        /**
         * 空闲广告
         */
        AD_IDLE(1),

        /**
         * 交易等待广告
         */
        AD_WAITING(0),

        /**
         * 跑马灯广告
         */
        AD_MESSAGE(2),

        /**
         * 公告广告
         */
        AD_BULLETIN(3);

        private int id;

        AdvertiseType(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public static AdvertiseType get(int id){
            for(AdvertiseType type : AdvertiseType.values()){
                if(type.getId() == id){
                    return type;
                }
            }
            return AdvertiseType.AD_IDLE;
        }
    }
}
