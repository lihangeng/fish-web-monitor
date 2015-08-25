(function() {

    Ext.Cometd = new org.cometd.Cometd();

    org.cometd.JSON.toJSON = Ext.encode;
    org.cometd.JSON.fromJSON = Ext.decode;

    var ResponseTransformer = function(packet) {
        return function(response, options) {
            packet.onSuccess(response.responseText);
        }
    };

    var XHRAborter = function(xhr) {
        return {
            abort : function() {
                Ext.Ajax.abort(xhr);
            }
        }
    };

    Ext.Cometd.LongPollingTransport = function() {

        this.xhrSend = function(packet) {

            var xhr = Ext.Ajax.request({
                url         : packet.url,
                method      : 'POST',
                headers     : packet.headers,
                jsonData    : packet.body,
                failure     : packet.onError,
                success     : new ResponseTransformer(packet)
            });

            return new XHRAborter(xhr);
        }
    };
    Ext.Cometd.LongPollingTransport.prototype = new org.cometd.LongPollingTransport();
    Ext.Cometd.LongPollingTransport.prototype.constructor = Ext.Cometd.LongPollingTransport;

    Ext.Cometd.CallbackPollingTransport = function() {

        this.jsonpSend = function(packet) {

            var xhr = Ext.Ajax.request({
                url         : packet.url,
                method      : 'GET',
                headers     : packet.headers,
                jsonp       : 'jsonp',
                jsonData    : {
                    message : packet.body
                },
                failure     : packet.onError,
                success     : new ResponseTransformer(packet)
            });

            return new XHRAborter(xhr);
        }
    };
    Ext.Cometd.CallbackPollingTransport.prototype = new org.cometd.CallbackPollingTransport();
    Ext.Cometd.CallbackPollingTransport.prototype.constructor = Ext.Cometd.CallbackPollingTransport;


    // Register transports
    Ext.Cometd.registerTransport('long-polling', new Ext.Cometd.LongPollingTransport());
    Ext.Cometd.registerTransport('callback-polling', new Ext.Cometd.CallbackPollingTransport());

})();