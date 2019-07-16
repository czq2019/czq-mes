<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div id="dialog-productUpdate-form" style="display: none;">
		<form id="productUpdateForm">
			<table
				class="table table-striped table-bordered table-hover dataTable no-footer"
				role="grid">
				<input type="hidden" name="id" id="input-Id2" value="" />
				<tr>
					<td><label for="productImgId">图号</label></td>
					<td><input id="input-orderId2" type="text" name="orderId"
						value="" class="text ui-widget-content ui-corner-all" readonly="true"></td>
				</tr>
				<tr>
					<td><label for="productMaterialname">材料名称</label></td>
					<td><input id="input-orderClientname2" type="text" name="orderClientname"
						value="" class="text ui-widget-content ui-corner-all"></td>
					<td><label for="orderProductname">材料来源</label></td>
					<td><input id="input-orderProductname2" type="text"
						name="orderProductname" value=""
						class="text ui-widget-content ui-corner-all"></td>
				</tr>
				<tr>
					<td><label for="orderContractid">工艺重量</label></td>
					<td><input id="input-orderContractid2" type="text" name="orderContractid"
						value="" class="text ui-widget-content ui-corner-all"></td>
					<td><label for="orderImgid">投料重量</label></td>
					<td><input id="input-orderImgid2" type="text" name="orderImgid" value=""
						class="text ui-widget-content ui-corner-all"></td>
				</tr>
				<tr>
					<td><label for="orderMaterialname">剩余重量</label></td>
					<td><input id="input-orderMaterialname2" type="text"
						name="orderMaterialname" value=""
						class="text ui-widget-content ui-corner-all"></td>
					<td><label for="orderCometime">锭型</label></td>
					<td><input id="input-orderCometime2" type="text" name="comeTime"
						value="" class="datepicker text ui-widget-content ui-corner-all"
						readonly="true"></td>
				</tr>
				<tr>
					<td><label for="productIrontype">钢锭类别</label></td>
					<td><input id="input-irontype" type="text" name="commitTime"
						value="" class="datepicker text ui-widget-content ui-corner-all"
						readonly="true"></td>
					<td><label for="productRemark">备注</label></td>
					<td><input id="input-productRemark2" type="text" name="orderRemark"
						value="" class="text ui-widget-content ui-corner-all"></td>
				</tr>
			</table>
		</form>
	</div>