/**
 * 多选框插件
 */
$(document).ready(function() {
	$('input').iCheck({
		checkboxClass : 'icheckbox_flat-green',
		radioClass : 'iradio_flat-green'
	});
});


/**
 * 初始化菜单树
 */
var ztreeObject;
var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "parentId",
			rootPId : 0
		},
		key : {
			name : 'name',
			title : 'name'
		}
	},
	check : {
		enable : true,
		nocheckInherit : true
	}
};
var treeData = [];
$(function() {
	var menus = eval('(' + menusJson + ')');

    for(var i  in menus){
		var parentNode = {};
		parentNode.id = menus[i].id;
		parentNode.name =  menus[i].name;
		parentNode.open = false;
		var childMenus =  menus[i].childMenus;
		var childNodes = [];
		for(var j in childMenus){
			var childNode = {};
            childNode.id = childMenus[j].id;
            childNode.parentId = menus[i].id;;
            childNode.name = childMenus[j].name;
            var actionMenus = childMenus[j].childMenus;
            var actionNodes = [];
            for(var k in actionMenus){
                var actionNode = {};
                actionNode.id = actionMenus[k].id;
                actionNode.parentId = actionMenus[k].id;
                actionNode.name = actionMenus[k].name;
                actionNodes.push(actionNode);
            }
            childNode.children = actionNodes;
            childNodes.push(childNode);
		}
		parentNode.children = childNodes;
		treeData.push(parentNode);
	}
	ztreeObject = $.fn.zTree.init($("#ztree"), setting, treeData);
    //展开所有节点
    ztreeObject.expandAll(true);
    //更新勾选操作
	try{
        if(permissionsJson !== undefined){
            initTreeData();
        }
	}catch(e){

	}
});

/**
 * 选中数据
 */
function initTreeData(){
    var permissions = eval('(' + permissionsJson + ')');
    console.log(permissions);
    for(var i = 0; i < permissions.length; i++){
        if(permissions[i].flag === 1 && permissions[i].menu.type === 0){
           var node = ztreeObject.getNodeByParam("id", permissions[i].menu.id, null);
           ztreeObject.checkNode(node, true, true);
        }
    }
}

/**
 * 表单验证
 */
$(function() {
	$('#form').bootstrapValidator({
		container : 'tooltip',
		message : 'This value is not valid',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			'roleName' : {
				message : '角色名称验证失败',
				validators : {
					notEmpty : {
						message : '角色名称不能为空'
					}
				}
			},
			'roleSign' : {
				message : '角色标志验证失败',
				validators : {
					notEmpty : {
						message : '角色标志不能为空'
					}
				}
			},
		}
	})
		.on('success.form.bv', function(e) {
			// Prevent form submission
			e.preventDefault();

			// Get the form instance
			var $form = $(e.target);

			// Get the BootstrapValidator instance
			var bv = $form.data('bootstrapValidator');

			var data = bulidData();
			var action = $('#form').attr('data-action');

			if (action === 'create') {
				$.ajax({
					data : JSON.stringify(data),
                    contentType:'application/json',
					dataType : 'json',
					type : 'post',
					url : '../role/create',
					success : function(result) {
						if (result.status === 'SUCCESS') {
							parent.layer.msg("创建成功!", {
								shade : 0.3,
								time : 1500
							}, function() {
								window.parent.location.reload(); // 刷新父页面
							});
						} else {
							layer.msg(result.errMsg, {
								icon : 2,
								time : 1000
							});
							$('[type="submit"]').enable();
						}
					}
				})
			} else if (action === 'update') {
				$.ajax({
					data : JSON.stringify(data),
					dataType : 'json',
					type : 'post',
					url : '../role/update',
                    contentType:'application/json',
					success : function(result) {
						if (result.status === 'SUCCESS') {
							parent.layer.msg("更新成功!", {
                                icon : 1,
								shade : 0.3,
								time : 1500
							}, function() {
								window.parent.location.reload(); // 刷新父页面
							});
						} else {
							layer.msg(result.errMsg, {
								icon : 2,
								time : 1000
							});
                            $('[type="submit"]').enable();
						}
					}
				})
			}
		});
});

/**
 * 构建json数据并返回
 */
function bulidData(){
    var role = {};
    role.id = $('#id').val();
    role.name = $('#name').val();
    role.status = $('[name="status"]:checked').val();
    role.remarks = $('#remarks').val();
    ztreeObject = $.fn.zTree.getZTreeObj("ztree");
    var nodes = ztreeObject.getCheckedNodes(true);
    var permissions = [];
    if (nodes !== null && nodes.length > 0) {
        for (var i = 0; i < nodes.length; i++) {
            var permission = {};
            var menu = {};
            menu.id = nodes[i].id;
            permission.flag = 1;
            permission.menu = menu;
            permissions.push(permission);
        }
    }
    role.permissions = permissions;
    return role;
}