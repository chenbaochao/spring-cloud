/*!Name: jQuery.imgScroll.js
 * Date: 2017-2-14 17:14:18 */
!function(e){e.fn.imgScroll=function(s,t){var i={data:[],template:null,evtType:"click",visible:1,direction:"x",next:"#next",prev:"#prev",disableClass:"disabled",disableClassPerfix:!1,speed:300,step:1,loop:!1,showControl:!1,width:null,height:null,navItems:!1,navItmesWrapClass:"scroll-nav-wrap",navItemActivedClass:"current",status:!1,statusWrapSelector:".scroll-status-wrap"},l=e.extend(i,s);return this.each(function(){function s(e){I||(v>w&&v>y?(x.addClass($),b.removeClass(k)):y>=v&&b.addClass(k)),"left"!==h.eq(0).css("float")&&h.css("float","left"),C=l.width||h.eq(0).outerWidth(),m=l.height||h.eq(0).outerHeight(),p.css({position:"static"==p.css("position")?"relative":p.css("position"),width:"x"==e?C*y:C,height:"x"==e?m:m*y,overflow:"hidden"}),u.css({position:"absolute",width:"x"==e?C*v:C,height:"x"==e?m:m*v,top:0,left:0}),"function"==typeof t&&t.apply(p,[g,q,h.slice(g*w,g*w+y),h.slice(g*w+y-w,g*w+y)])}function i(){v=l.data.length,u=p.find("ul").eq(0),h=u.children("li"),q=Math.ceil((v-y)/w)+1,D=(v-y)%w===0}function a(e){var s={list:e};p.html(E.process(s)),i()}function n(e,s){function i(){I?(z=y>=v-e*w,B=0>=e):(y>=v-e*w?(b.addClass(k),z=!0):(b.removeClass(k),z=!1),0>=e?(x.addClass($),B=!0):(x.removeClass($),B=!1)),(A||H)&&o(e),"function"==typeof t&&t.apply(p,[e,q,h.slice(e*w,e*w+y),h.slice(e*w+y-w,e*w+y)])}if(u.is(":animated"))return!1;if(I)B&&s&&(g=q),z&&!s&&(g=-1),e=s?--g:++g;else{if(B&&s||z&&!s)return!1;e=s?--g:++g}f="x"==P?{left:e>=q-1?-(v-y)*C:-e*w*C}:{top:e>=q-1?-(v-y)*m:-e*w*m},l.speed?u.animate(f,l.speed,i):(u.css(f),i())}function d(s,t){for(var i=T?e("."+s).eq(0):e('<div class="'+s+'"></div>'),l=0;q>l;l++)i.append("<em "+(0===l?" class="+t:"")+' title="'+(l+1)+'">'+(l+1)+"</em>");T||p.after(i)}function r(){var s=_?e(Q).eq(0):e('<div class="'+Q.replace(".","")+'"></div>');s.html("<b>1</b>/"+q),_||p.after(s)}function o(s){A&&e("."+M).find("em").removeClass(j).eq(s).addClass(j),H&&e(Q).html("<b>"+(s+1)+"</b>/"+q)}function c(){x.unbind(W).bind(W,function(){n(g,!0)}),b.unbind(W).bind(W,function(){n(g,!1)})}var f,p=e(this),u=p.find("ul").eq(0),h=u.children("li"),v=h.length,C=null,m=null,b="string"==typeof l.next?e(l.next):l.next,x="string"==typeof l.prev?e(l.prev):l.prev,g=0,w=l.step,y=l.visible,q=Math.ceil((v-y)/w)+1,I=l.loop,P=l.direction,W=l.evtType,S=l.disableClass,$=l.disableClassPerfix?l.disableClassPerfix+"-prev-"+S:S,k=l.disableClassPerfix?l.disableClassPerfix+"-next-"+S:S,A=l.navItems,M=l.navItmesWrapClass,T=e("."+M).length>0,j=l.navItemActivedClass,H=l.status,Q=l.statusWrapSelector,_=e(Q).length>0,z=!1,B=!0,D=(v-y)%w===0,E=l.template||'<ul>{for slide in list}<li><a href="${slide.href}" target="_blank"><img src="${slide.src}" alt="${slide.alt}" /></a></li>{/for}</ul>';if(l.data.length>0){if(!l.width||!l.height)return!1;a(l.data)}v>y&&y>=w?(s(P),c(),A&&d(M,j),H&&r(Q)):(l.showControl?b.add(x).show():b.add(x).hide(),x.addClass($),b.addClass(k))})}}(jQuery);
