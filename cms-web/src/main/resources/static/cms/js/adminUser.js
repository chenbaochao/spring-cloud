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
			'username' : {
				message : '用户名验证失败',
				validators : {
					notEmpty : {
						message : '用户名不能为空'
					}
				}
			},
			'password' : {
				message : '密码验证失败',
				validators : {
					notEmpty : {
						message : '密码不能为空'
					}
				}
			},
			'telephone' : {
				validators : {
					notEmpty : {
						message : '移动电话不能为空'
					},
					regexp : {
						regexp : /^1[3|4|5|7|8]\d{9}$/,
						message : '手机号码格式不正确'
					}
				}
			},
			'email' : {
				validators : {
					notEmpty : {
						message : '电子邮箱不能为空'
					},
					regexp : {
						regexp : /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,
						message : '电子邮箱格式不正确'
					}
				}
			}
		}
	})
		.on('success.form.bv', function(e) {
			// Prevent form submission
			e.preventDefault();

			// Get the form instance
			var $form = $(e.target);

			// Get the BootstrapValidator instance
			var bv = $form.data('bootstrapValidator');
			var action = $form.data('action');
			// 更新
			if ('update' === action) {
				$.ajax({
					data : $form.serialize(),
					dataType : 'json',
					type : 'put',
					url : '/administrator',
					success : function(result) {
						if (result.status === 'SUCCESS') {
							parent.layer.msg("更新管理员成功!", {
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
						}
					}
				})

			} else if ('create' === url) {
				$.ajax({
					data : $form.serialize(),
					dataType : 'json',
					type : 'post',
                    url : '/administrator',
					success : function(result) {
						if (result.status === 'SUCCESS') {
							parent.layer.msg("创建管理员成功!", {
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
						}
					}
				})
			}
		});
})