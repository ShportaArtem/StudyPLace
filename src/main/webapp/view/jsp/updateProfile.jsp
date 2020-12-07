<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!doctype html>
<html lang="en">
<style>
button.btn-info{
background:#27A8CF;
}
button.btn-info:hover{
background:#14729D;
}
</style>
<%@ include file="/view/jspf/head.jspf"%>
<body class="bg-secondary">
<%@ include file="/view/jspf/header2.jspf"%>
<div class="container">
    <div class="row">
        
        <div class="col-md-12">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-12">
                            <h4 style="color: #083A6;">Your Profile</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <form id="updateProfile_form" action="Controller" method="post">
                            <input type="hidden" name="command" value="updateProfile" />

                              <div class="form-group row">
                                <label for="username" class="col-4 col-form-label" style="color: #083A6;">Login</label> 
                                <div class="col-8">
                                  <input id="username" name="username" style="color: #083A6;" placeholder="${sessionScope.user.getLogin()}" class="form-control here"  type="text">
                                </div>
                              </div>

                              <div class="form-group row">
                                <label for="name" class="col-4 col-form-label" style="color: #083A6;">Name</label> 
                                <div class="col-8">
                                  <input id="name" name="name" style="color: #083A6;" placeholder="${sessionScope.user.getName()}" class="form-control here" type="text">
                                </div>
                              </div>

                              <div class="form-group row">
                                <label for="lastname" class="col-4 col-form-label" style="color: #083A6;">Surname</label> 
                                <div class="col-8">
                                  <input id="lastname" name="surname" style="color: #083A6;" placeholder="${sessionScope.user.getSurname()}" class="form-control here" type="text">
                                </div>
                              </div>
							
							<hr>
							<!-- ~~~~~~~~~~~~~~~~~~~~~~~ -->
							<c:choose>
                                <c:when test="${!sessionScope.thisUserInfo.getPicture().equals('')}">
                                	<div style="display: block; width:100%;">
                                    	<label for="userpicture" style="color: #083A6;">Picture</label> 
                                    </div>
                                    <div style="display: block; margin-bottom:24px; width:100%;">
                                    	<input id="userpicture" name="picture" style="color: #083A6;"  type="file">
                                    </div>
                                </c:when>  
                                  
                                <c:otherwise>
                                   <div style="display: block; width:100%;">
                                    	<label for="userpicture" style="color: #083A6;">Picture</label> 
                                    </div>
                                    <div style="display: block; margin-bottom:24px; width:100%;">
                                    	<input id="userpicture" name="picture" style="color: #083A6;"  type="file">
                                    </div>
                                </c:otherwise>
                            </c:choose>
							<!-- ~~~~~~~~~~~~~~~~~~~~~~~ -->
							
                            <c:choose>
                                <c:when test="${!sessionScope.thisUserInfo.getAbout().equals('')}">
                                    <div class="form-group row">
                                        <label for="text" class="col-4 col-form-label" style="color: #083A6;">About</label> 
                                        <div class="col-8">
                                          <input id="text" name="about" style="color: #083A6;" value="${sessionScope.thisUserInfo.getAbout()}" class="form-control here"  type="text">
                                        </div>
                                      </div>
                                </c:when>  
                                  
                                <c:otherwise>
                                   <div class="form-group row">
                                        <label for="text" class="col-4 col-form-label" style="color: #083A6;">About</label> 
                                        <div class="col-8">
                                          <input id="text" name="about" style="color: #083A6;" placeholder="enter about you" class="form-control here"  type="text">
                                        </div>
                                   </div>
                                </c:otherwise>
                            </c:choose>


                            <c:choose>
                                <c:when test="${!sessionScope.thisUserInfo.getEmail().equals('')}">
                                    <div class="form-group row">
                                        <label for="text" class="col-4 col-form-label" style="color: #083A6;">Email</label> 
                                        <div class="col-8">
                                          <input id="text" name="e-mail" style="color: #083A6;" value="${sessionScope.thisUserInfo.getEmail()}" class="form-control here"  type="email">
                                        </div>
                                      </div>
                                </c:when>  
                                  
                                <c:otherwise>
                                   <div class="form-group row">
                                        <label for="text" class="col-4 col-form-label" style="color: #083A6;">Email</label> 
                                        <div class="col-8">
                                          <input id="text" name="e-mail" style="color: #083A6;" placeholder="enter you email" class="form-control here"  type="email">
                                        </div>
                                      </div>
                                </c:otherwise>
                            </c:choose>

                             <c:choose>
                                <c:when test="${!sessionScope.thisUserInfo.getMessanger().equals('')}">
                                    <div class="form-group row">
                                        <label for="text" class="col-4 col-form-label" style="color: #083A6;">Messenger</label> 
                                        <div class="col-8">
                                          <input id="text" name="messanger" style="color: #083A6;" value="${sessionScope.thisUserInfo.getMessanger()}" class="form-control here"  type="text">
                                        </div>
                                      </div>
                                </c:when>  
                                  
                                <c:otherwise>
                                   <div class="form-group row">
                                        <label for="text" class="col-4 col-form-label" style="color: #083A6;">Messenger</label> 
                                        <div class="col-8">
                                          <input id="text" name="messanger" style="color: #083A6;" placeholder="give a link" class="form-control here"  type="text">
                                        </div>
                                      </div>
                                </c:otherwise>
                            </c:choose>

                             <c:choose>
                                <c:when test="${!sessionScope.thisUserInfo.getWebsite().equals('')}">
                                    <div class="form-group row">
                                        <label for="text" class="col-4 col-form-label" style="color: #083A6;">Web-site</label> 
                                        <div class="col-8">
                                          <input id="text" name="website" style="color: #083A6;" value="${sessionScope.thisUserInfo.getWebsite()}" class="form-control here"  type="text">
                                        </div>
                                      </div>
                                </c:when>  
                                  
                                <c:otherwise>
                                   <div class="form-group row">
                                        <label for="text" class="col-4 col-form-label" style="color: #083A6;">Web-site</label> 
                                        <div class="col-8">
                                          <input id="text" name="website" style="color: #083A6;" placeholder="give a link" class="form-control here"  type="text">
                                        </div>
                                      </div>
                                </c:otherwise>
                            </c:choose>


<div class="form-group">
   <button type="submit" class="btn btn-info mb-2" style="color: #F5EFE5; background-color: #27A8CF;"><fmt:message key="button.confirm" /></button>
   <button type="button" class="btn btn-info mb-2" style="color: #F5EFE5; background-color: #27A8CF;" onclick="javascript:history.back()"><fmt:message key="button.back" /></button>
</div>



                              
                            </form>
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/view/jspf/footer.jspf"%>
<%@ include file="/view/jspf/bodyScripts.jspf"%>
</body>
</html>