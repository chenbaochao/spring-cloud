/* jdf-1.0.0/ myjd.js Date:2017-02-09 17:08:53 */
define("/js/myjd.js",["/js/login.js","/js/dropdown.js","/js/globalReco.js","/js/cookie.js"],function(require){var c=require("/js/login.js");require("/js/dropdown.js");var e=require("/js/globalReco.js");var f=require("/js/cookie.js");var g={init:function(){var a=$("#ttbar-myjd");a.find(".dd").html('<div class="dd-spacer"></div><div class="dd-inner"><span class="loading"></span></div>'),this.el=a,this.bind()},bind:function(){var b=this;this.el.dropdown({enterDelay:100,trigger:!0,current:"hover",onchange:function(a){a.attr("data-load")||(a.attr("data-load",1),b.checkLoginInit())}})},checkLoginInit:function(){var a=this;c({automatic:!0,complete:function(b){if(b){var c="";b.Identity.IsAuthenticated?(c=a.tpl(1,b.Identity.Unick),a.hasLoginInit()):c=a.tpl(0,""),a.el.find(".dd").html(c),a.viewlist(),a.baitiaoInit()}}})},tpl:function(){var c='<div class="dd-spacer"></div>				<div class="myjdlist1">					<div class="fore1">						<div class="item">							<a href="//order.jd.com/center/list.action" clstag="" target="_blank">\u5f85\u5904\u7406\u8ba2\u5355<span id="num-unfinishedorder"></span></a>						</div>						<div class="item">							<a href="//myjd.jd.com/afs/indexNew.action?entry=1" clstag="" target="_blank">\u8fd4\u4fee\u9000\u6362\u8d27</a>						</div>						<div class="item">							<a href="//t.jd.com/product/followProductList.action?isReduce=true" clstag="" target="_blank">\u964d\u4ef7\u5546\u54c1<span id="num-reduction"></span></a>						</div>					</div>					<div class="fore2">						<div class="item">							<a href="//joycenter.jd.com/msgCenter/queryHistoryMessage.action" target="_blank">\u6d88\u606f<span id="num-tip"></span></a>						</div>						<div class="item">							<a href="http://question.jd.com/myjd/getMyjdAnswerList.action" clstag="" target="_blank">\u6211\u7684\u95ee\u7b54<span id="num-consultation"></span></a>						</div>						<div class="item">							<a href="//t.jd.com/home/follow" clstag="" target="_blank">\u6211\u7684\u5173\u6ce8</a>						</div>					</div>				</div>				<div class="myjdlist2">					<div class="fore1">						<div class="item">							<a href="//bean.jd.com/myJingBean/list" clstag="" target="_blank">\u6211\u7684\u4eac\u8c46</a>						</div>						<div class="item baitiao">							<a href="//baitiao.jd.com/" clstag="jr|keycount|njdhome|wdbaitiao" target="_blank">\u6211\u7684\u767d\u6761</a>						</div>					</div>					<div class="fore2">						<div class="item">							<a href="//quan.jd.com/user_quan.action" target="_blank">\u6211\u7684\u4f18\u60e0\u5238<span id="num-ticket"></span></a>						</div>						<div class="item">							<a href="//trade.jr.jd.com/centre/browse.action" clstag="" target="_blank">\u6211\u7684\u7406\u8d22</a>						</div>					</div>				</div>				<div class="viewlist" style="display:none;">					<div class="smt">						<h4>\u6211\u7684\u8db3\u8ff9</h4>						<span class="extra">							<a target="_blank" href="//my.jd.com/history/list.html">\u66f4\u591a&nbsp;&gt;</a>						</span>					</div>					<div class="smc"></div>				</div>			';return c},hasLoginInit:function(){var a=this;$.ajax({url:"//minijd.jd.com/getHomeCount",dataType:"jsonp",success:function(b){b&&0==b.error&&a.el.find("#num-unfinishedorder").html(a.numStyleSet(b.orderCount))}}),$.ajax({url:"//question.jd.com/myjd/getMyJdAnswerCount.action",dataType:"jsonp",success:function(b){var c=b.data;c.success&&a.el.find("#num-consultation").html(a.numStyleSet(c.result.answerCount))}}),$.ajax({url:"//follow-soa.jd.com/rpc/product/queryForReduceProductCount.action?",data:{sysName:"misc"},dataType:"jsonp",success:function(b){b&&b.data>0&&a.el.find("#num-reduction").html(a.numStyleSet(b.data))}}),$.ajax({url:"//quan.jd.com/getcouponcount.action",dataType:"jsonp",success:function(b){b&&a.el.find("#num-ticket").html(a.numStyleSet(b.CouponCount))}}),$.ajax({url:"//joycenter.jd.com/msgCenter/init.action",dataType:"jsonp",success:function(b){b&&"G001001"==b.result&&a.el.find("#num-tip").html(a.numStyleSet(b.msgUnreadCount))}}),$.ajax({url:"//i.jd.com/user/petName/getUserInfoForMiniJd.action",dataType:"jsonp",success:function(b){if(b){b.imgUrl&&a.el.find(".u-pic img").attr("src",b.imgUrl);var c=b.userLevel;if(c){var d={1:"\u6ce8\u518c\u4f1a\u5458",2:"\u94dc\u724c\u4f1a\u5458",3:"\u94f6\u724c\u4f1a\u5458",4:"\u91d1\u724c\u4f1a\u5458",5:"\u94bb\u77f3\u4f1a\u5458"};$("#userLevel").attr({"class":"user-level"+c,title:d[c]})}}}})},numStyleSet:function(a){return 0==a?"":'<span class="num style-red">&nbsp;'+a+"</span>"},viewlist:function(){var a=this;new e({$el:$("#jduc-viewlist"),skuHooks:"SKUS_recent_view",template:"",param:{p:202001,sku:"",ck:"pin,ipLocation,atw,aview",lim:5},callback:function(b,c){if(b&&c){var d="";c=c.data;var e=0;$.each(c,function(a,b){4>e&&b.sku&&b.img&&(d+='<div class="item"><a href="//item.jd.com/'+b.sku+'.html" target="_blank" title="'+b.t+'"><img src="'+pageConfig.FN_GetImageDomain(b.sku)+"n5/"+b.img+'" width="50" height="50" alt="'+b.t+'" /></a></div>',e++)});var f=a.el.find(".viewlist");f.find(".smc").html(d),f.show()}}})},baitiaoInit:function(){var b=this;f("pin")?this.baitiaoLinkSet(function(a){var c=b.el.find(".baitiao");a.btName&&a.btUrl&&c.html('<a href="'+a.btUrl+'" target="_blank">'+a.btName+"</a>")}):b.el.find(".baitiao").show()},baitiaoLinkSet:function(a){$.ajax({url:"//btshow.jd.com/ious/queryBT.do?sourceType=137",type:"get",dataType:"jsonp",scriptCharset:"utf-8",success:function(b){b&&b.btList&&b.btList[0]&&a(b.btList[0])}})}};function h(){g.init()}return h});
