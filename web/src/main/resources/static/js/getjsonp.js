/* jdf-1.0.0/ getjsonp.js Date:2017-02-09 17:08:53 */
!function($){$.extend({_jsonp:{scripts:{},counter:1,charset:"gb2312",head:document.getElementsByTagName("head")[0],name:function(callback){var name="_jsonp_"+(new Date).getTime()+"_"+this.counter;this.counter++;var cb=function(json){eval("delete "+name),callback(json),$._jsonp.head.removeChild($._jsonp.scripts[name]),delete $._jsonp.scripts[name]};return eval(name+" = cb"),name},load:function(a,b,c,d){var e=document.createElement("script");e.type=c||"text/javascript",e.charset=d||this.charset,e.src=a,this.head.appendChild(e),this.scripts[b]=e}},getJSONP:function(a,b,c,d){var e=$._jsonp.name(b);var a=a.replace(/{callback};/,e);return $._jsonp.load(a,e,c,d),this}})}(jQuery);