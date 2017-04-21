
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!--弹出删除-->
<div class="modal fade small_modal" id="delete" tabindex="-1" role="dialog" style="display:none;" >
    <div class="modal-dialog">
      <div class="modal-content">
        
        <div class="modal-body" >
         <p align="center" style="font-family:'微雅软黑'; font-size:24px;" id="del_assert_id"></p>
        </div>
        <form action="">
        	<input id="del" type="hidden" name="assert_id">
			<input type="hidden" name="currentPage" value="1"  />
			<input type="hidden" name="oper" value="delect"  />
         <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">取消 </button>
          <button type="submit" class="btn btn-primary" > 确认 </button>
        </div>
        </form>
       
      </div>
      <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
  </div>