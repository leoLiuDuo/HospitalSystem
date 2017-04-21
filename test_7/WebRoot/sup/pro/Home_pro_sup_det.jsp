<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="modal fade " id="del1" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">详细信息</h4>
				</div>
				<div class="modal-body" style="width:300px;height:250px;">
				
								<table class="table table-bordered table-stripped">
									<tr>
									 <td colspan="4">资产信息</td>
									</tr>
									<tr>
										<td >资产编号</td>
										<td id="asset_id" ></td>
										<td align="center">资产配置信息</td>
										<td align="center"id="allo_info"></td>
									</tr>
									<tr>
										<td >资产存放地点</td>
										<td id="storage_loca"> </td>
										<td align="center">资产属性</td>
										<td align="center"id="asset_attribute"></td>
									</tr>
									<tr>
										<td >资产状态</td>
										<td id="asset_status"> </td>
										<td align="center">资产使用状态</td>
										<td align="center"id="asset_use_status"></td>
									</tr>
									<tr>
										<td >设备应用状态</td>
										<td id="dev_appli_sta"> </td>
										<td align="center">资产购置类型</td>
										<td align="center"id="asset_acq_type"></td>
									</tr>
									<tr>
										<td >代维单位合同期限</td>
										<td id="daiWei_cont_peri"> </td>
										<td align="center">服务终止时间</td>
										<td align="center"id="serv_end_time"></td>
									</tr>
									<tr>
										<td >保修期</td>
										<td id="maint_period"> </td>
										<td align="center">保修终止时间</td>
										<td align="center"id="maint_end_time"></td>
									</tr>
									
									<tr>
										<td >用户id</td>
										<td id="user_id"> </td>
										<td align="center">用户姓名</td>
										<td align="center"id="name"></td>
									</tr>
									<tr>
										<td >资产主管单位</td>
										<td id="ass_controller"> </td>
										<td align="center"></td>
										<td align="center"></td>
									</tr>
									<tr>
									 <td colspan="4">代维单位</td>
									</tr>
									<tr>
										<td >代维单位编号</td>
										<td id="daiwei_id"> </td>
										<td align="center">代维单位名称</td>
										<td align="center"id="daiwei_unit"></td>
									</tr>
									<tr>
										<td >邮政编码</td>
										<td id="postcode"> </td>
										<td align="center">单位负责人</td>
										<td align="center"id="unit_res"></td>
									</tr>
									<tr>
										<td >白天固定电话</td>
										<td id="tel_day"> </td>
										<td align="center">夜间固定电话</td>
										<td align="center"id="tel_night"></td>
									</tr>
									<tr>
										<td >移动电话</td>
										<td id="mol_tel"> </td>
										<td align="center">Email地址</td>
										<td align="center"id="email"></td>
									</tr>
									<tr>
										<td >代维单位地址</td>
										<td id="address"> </td>
										<td align="center">代维主管单位</td>
										<td align="center"id="dai_controller"></td>
									</tr>
									<tr>
									 <td colspan="4">项目信息</td>
									</tr>
									<tr>
										<td >项目编号</td>
										<td id="pro_id"> </td>
										<td align="center">项目名称</td>
										<td align="center"id="pro_name"></td>
									</tr>
									<tr>
										<td >批复文号</td>
										<td id="aproval_num"> </td>
										<td align="center">项目类型名称</td>
										<td align="center"id="pro_type"></td>
									</tr>
									<tr>
									 <td colspan="4">设备信息</td>
									</tr>
									<tr>
										<td >设备型号</td>
										<td id="dev_mod"> </td>
										<td align="center">设备名称</td>
										<td align="center"id="dev_name"></td>
									</tr>
									<tr>
										<td >计量单位</td>
										<td id="meas_name"> </td>
										<td align="center"></td>
										<td align="center"id="dev_type"></td>
									</tr>
									<tr>
										<td >生产厂家</td>
										<td id="prod_name_del"> </td>
										<td align="center">资产类型</td>
										<td align="center"id="asset_type_del"></td>
									</tr>
									<tr>
									 <td colspan="4">商务合同</td>
									</tr>
									<tr>
										<td >合同编号</td>
										<td id="cont_id"> </td>
										<td align="center">合同名称</td>
										<td align="center"id="cont_name"></td>
									</tr>
									<tr>
										<td >购置日期</td>
										<td id="buy_date"> </td>
										<td align="center">起用时间</td>
										<td align="center"id="start_date"></td>
									</tr>
									
								
</table>
</div>
</div>
</div>
</div>
