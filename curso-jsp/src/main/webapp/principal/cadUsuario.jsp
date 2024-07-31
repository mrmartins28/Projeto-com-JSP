<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

  <body>

<jsp:include page="theme-loader.jsp"></jsp:include>

  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
      
      	<jsp:include page="navbarTop.jsp"></jsp:include>
   

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
                 
                   <jsp:include page="navbarmainmenu.jsp"></jsp:include>
                 
                  <div class="pcoded-content">
                     
                     
                     <jsp:include page="page-header.jsp"></jsp:include>
                     
                      <!-- Page-header end -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                        <div class="row">
                                        
									<h1>Pagina de cadastro de usuario</h1>
                                            
                                        </div>
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    <jsp:include page="javascriptFile.jsp"></jsp:include>
    
</body>

</html>
