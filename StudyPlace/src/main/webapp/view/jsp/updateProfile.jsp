<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!doctype html>
<html lang="en">
<%@ include file="/view/jspf/head.jspf"%>
<body class="bg-secondary">
<%@ include file="/view/jspf/header_main.jspf"%>
<div class="container">
    <div class="row">
        
        <div class="col-md-12">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-12">
                            <h4>Your Profile</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <form id="updateProfile_form" action="Controller" method="post">
                            <input type="hidden" name="command" value="updateProfile" />

                              <div class="form-group row">
                                <label for="username" class="col-4 col-form-label">Login</label> 
                                <div class="col-8">
                                  <input id="username" name="username" placeholder="${sessionScope.user.getLogin()}" class="form-control here"  type="text">
                                </div>
                              </div>

                              <div class="form-group row">
                                <label for="name" class="col-4 col-form-label">Name</label> 
                                <div class="col-8">
                                  <input id="name" name="name" placeholder="${sessionScope.user.getName()}" class="form-control here" type="text">
                                </div>
                              </div>

                              <div class="form-group row">
                                <label for="lastname" class="col-4 col-form-label">Surname</label> 
                                <div class="col-8">
                                  <input id="lastname" name="surname" placeholder="${sessionScope.user.getSurname()}" class="form-control here" type="text">
                                </div>
                              </div>

                            <c:choose>
                                <c:when test="${!sessionScope.thisUserInfo.getAbout().equals('')}">
                                    <div class="form-group row">
                                        <label for="text" class="col-4 col-form-label">About</label> 
                                        <div class="col-8">
                                          <input id="text" name="about" placeholder="${sessionScope.thisUserInfo.getAbout()}" class="form-control here"  type="text">
                                        </div>
                                      </div>
                                </c:when>  
                                  
                                <c:otherwise>
                                   <div class="form-group row">
                                        <label for="text" class="col-4 col-form-label">About</label> 
                                        <div class="col-8">
                                          <input id="text" name="about" placeholder="enter about you" class="form-control here"  type="text">
                                        </div>
                                   </div>
                                </c:otherwise>
                            </c:choose>


                            <c:choose>
                                <c:when test="${!sessionScope.thisUserInfo.getEmail().equals('')}">
                                    <div class="form-group row">
                                        <label for="text" class="col-4 col-form-label">Email</label> 
                                        <div class="col-8">
                                          <input id="text" name="e-mail" placeholder="${sessionScope.thisUserInfo.getEmail()}" class="form-control here"  type="email">
                                        </div>
                                      </div>
                                </c:when>  
                                  
                                <c:otherwise>
                                   <div class="form-group row">
                                        <label for="text" class="col-4 col-form-label">Email</label> 
                                        <div class="col-8">
                                          <input id="text" name="e-mail" placeholder="enter you email" class="form-control here"  type="email">
                                        </div>
                                      </div>
                                </c:otherwise>
                            </c:choose>

                             <c:choose>
                                <c:when test="${!sessionScope.thisUserInfo.getMessanger().equals('')}">
                                    <div class="form-group row">
                                        <label for="text" class="col-4 col-form-label">Messenger</label> 
                                        <div class="col-8">
                                          <input id="text" name="messanger" placeholder="${sessionScope.thisUserInfo.getMessanger()}" class="form-control here"  type="text">
                                        </div>
                                      </div>
                                </c:when>  
                                  
                                <c:otherwise>
                                   <div class="form-group row">
                                        <label for="text" class="col-4 col-form-label">Messenger</label> 
                                        <div class="col-8">
                                          <input id="text" name="messanger" placeholder="give a link" class="form-control here"  type="text">
                                        </div>
                                      </div>
                                </c:otherwise>
                            </c:choose>

                             <c:choose>
                                <c:when test="${!sessionScope.thisUserInfo.getWebsite().equals('')}">
                                    <div class="form-group row">
                                        <label for="text" class="col-4 col-form-label">Web-site</label> 
                                        <div class="col-8">
                                          <input id="text" name="website" placeholder="${sessionScope.thisUserInfo.getWebsite()}" class="form-control here"  type="text">
                                        </div>
                                      </div>
                                </c:when>  
                                  
                                <c:otherwise>
                                   <div class="form-group row">
                                        <label for="text" class="col-4 col-form-label">Web-site</label> 
                                        <div class="col-8">
                                          <input id="text" name="website" placeholder="give a link" class="form-control here"  type="text">
                                        </div>
                                      </div>
                                </c:otherwise>
                            </c:choose>


<div class="form-group">
   <button type="submit" class="btn btn-success mb-2"><fmt:message key="button.confirm" /></button>
   <button type="button" class="btn btn-primary mb-2" onclick="javascript:history.back()"><fmt:message key="button.back" /></button>
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