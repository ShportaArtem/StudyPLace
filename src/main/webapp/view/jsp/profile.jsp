<%@ include file="/view/jspf/page.jspf"%>
<%@ include file="/view/jspf/taglib.jspf"%>
<!doctype html>
<html lang="en">
<style>
a.btn-info{
background:#27A8CF;
}
a.btn-info:hover{
background:#14729D;
}
</style>
<%@ include file="/view/jspf/head.jspf"%>
<body class="bg-secondary">
<%@ include file="/view/jspf/header2.jspf"%>

<div class="container-fluid">

<div class="container">
        <div class="row">
            <div class="col-12">
                <div class="card">

                    <div class="card-body">
                        <div class="card-title mb-4">
                            <div class="d-flex justify-content-start">
                                <div class="image-container">
	                            <c:choose>
	                                <c:when test="${sessionScope.thisUserInfo != null && sessionScope.thisUserInfo.getPicture() != null}">
	                                	<img src="data:image/jpg;base64,${sessionScope.thisUserInfo.getPicture64()}" id="imgProfile" style="width: 150px; height: 150px; border-radius: 75px; margin-right: 20px;" class="img-thumbnail" />
	                                </c:when>  
	                                  
	                                <c:otherwise>
	                                   <img src="http://placehold.it/150x150" id="imgProfile" style="width: 150px; height: 150px" class="img-thumbnail" />
	                                </c:otherwise>
	                            </c:choose>
                                    
                                    
                                </div>
                                <div class="userData ml-3">
                                    <h2 class="d-block" style="color:#083A63; font-size: 1.5rem;  font-weight: bold; text-transform: uppercase;">${sessionScope.user.getName()} ${sessionScope.user.getSurname()}</h2>
                                    <h6 class="d-block" style="color:#14729D;">0 Sertificates</h6>
                                    <h6 class="d-block" style="color:#14729D;">0 Points</h6>
                                </div>
                                
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active" id="basicInfo-tab" data-toggle="tab" href="#basicInfo" role="tab" aria-controls="basicInfo" aria-selected="true" style="color:#F55426;">Basic Info</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="connectedServices-tab" data-toggle="tab" href="#connectedServices" role="tab" aria-controls="connectedServices" aria-selected="false" style="color:#F55426;">Certificates</a>
                                    </li>
                                </ul>
                                <div class="tab-content ml-1" id="myTabContent">
                                    <div class="tab-pane fade show active" id="basicInfo" role="tabpanel" aria-labelledby="basicInfo-tab">
                                        

                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Login</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                ${sessionScope.user.getLogin()}
                                            </div>
                                        </div>
                                        <hr />

                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Name</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                ${sessionScope.user.getName()}
                                            </div>
                                        </div>
                                        <hr />
                                        
                                        
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Surname</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                ${sessionScope.user.getSurname()}
                                            </div>
                                        </div>
                                        <hr />
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">About</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                ${sessionScope.thisUserInfo.getAbout()}
                                            </div>
                                        </div>
                                        <hr />
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Email</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                ${sessionScope.thisUserInfo.getEmail()}
                                            </div>
                                        </div>
                                        <hr />
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Messenger</label>
                                            </div>
                                            <a href="${sessionScope.thisUserInfo.getMessanger()}" class="col-md-8 col-6">
                                                ${sessionScope.thisUserInfo.getMessanger()}
                                            </a>
                                        </div>
                                        <hr />
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Web-site</label>
                                            </div>
                                            <a href ="${sessionScope.thisUserInfo.getWebsite()}" class="col-md-8 col-6">
                                                ${sessionScope.thisUserInfo.getWebsite()}
                                            </a>
                                        </div>
                                        <hr/>
                                        <div class="text-center">
						                	<a class="btn btn-info my-2 my-sm-0"  type="submit" href="/StudyPlace/Controller?command=getUpdateProfile">Edit Profile</a>
											
						                </div>
						                <hr/>

                                    </div>
                                    <div class="tab-pane fade" id="connectedServices" role="tabpanel" aria-labelledby="ConnectedServices-tab">
                                        Place for certificates.
                                    </div>
                                </div>
                            </div>
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