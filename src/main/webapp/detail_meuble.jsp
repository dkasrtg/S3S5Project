<%@ page isErrorPage="true" %>
<%@ page import="entity.meuble.*" %>
<%@ page import="entity.materiau.*" %>
<%@ page import="java.util.List" %>
<%
VMeuble vMeuble = (VMeuble) request.getAttribute("vMeuble");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui"
    />
    <title>Meuble - Detail</title>
    <meta content="Admin Dashboard" name="description" />
    <meta content="Mannatthemes" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="shortcut icon" href="/template/assets/images/favicon.ico" />
    <link
      href="/template/assets/plugins/datatables/dataTables.bootstrap4.min.css"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="/template/assets/plugins/datatables/buttons.bootstrap4.min.css"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="/template/assets/css/bootstrap.min.css"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="/template/assets/css/icons.css"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="/template/assets/css/style.css"
      rel="stylesheet"
      type="text/css"
    />
  </head>
  <body class="fixed-left">
    <div id="wrapper">
      <%@ include file="/statics/sidebar.jsp"%>
      <div class="content-page">
        <div class="content">
          <%@ include file="/statics/topbar.jsp"%>
          <div class="page-content-wrapper">
            <div class="container-fluid">
              <div class="row">
                <div class="col-sm-12">
                  <div class="page-title-box">
                    <div class="btn-group float-right">
                      <ol class="breadcrumb hide-phone p-0 m-0">
                        <li class="breadcrumb-item">
                          <a href="#">Meuble</a>
                        </li>
                        <li class="breadcrumb-item active">Detail</li>
                      </ol>
                    </div>
                    <h4 class="page-title">Detail</h4>
                  </div>
                </div>
              </div>
              <!-- Eto no atao ny page -->
              <div class="row">
                <div class="col-12">
                  <div class="card">
                    <div class="card-body">
                      <h4 class="mt-0 header-title">Details meuble <%= vMeuble.getId() %></h4>
                      <div class="row">
                        <div class="col-6">
                          <div class="table-responsive">
                            <table class="table mb-0 table-centered">
                              <tbody>
                                <tr>
                                  <td>Nom:</td>
                                  <td><%= vMeuble.getNom() %></td>
                                </tr>
                                <tr>
                                  <td>Categorie:</td>
                                  <td><%= vMeuble.getNomCategorieMeuble() %></td>
                                </tr>
                                <tr>
                                  <td>Style:</td>
                                  <td><%= vMeuble.getNomStyleMeuble() %></td>
                                </tr>
                                <tr>
                                  <td>Lieux:</td>
                                  <td>
                                    <%
                                    for (VLieuPossibleMeuble vlpm : vMeuble.getVLieuPossibleMeuble()){
                                      out.print(vlpm.getNom() + " ; ");
                                    }
                                    %>
                                  </td>
                                </tr>
                                <tr>
                                  <td>Desciption:</td>
                                  <td><%= vMeuble.getDescription() %></td>
                                </tr>
                                <tr>
                                  <td>Longueur:</td>
                                  <td><%= vMeuble.getLongueur() %></td>
                                </tr>
                                <tr>
                                  <td>Largeur:</td>
                                  <td><%= vMeuble.getLargeur() %></td>
                                </tr>
                                <tr>
                                  <td>Hauteur:</td>
                                  <td><%= vMeuble.getHauteur() %></td>
                                </tr>
                                <tr>
                                  <td>Volume:</td>
                                  <td><%= vMeuble.getVolume() %></td>
                                </tr>
                                <tr>
                                  <td>Volume materiau:</td>
                                  <td><%= vMeuble.getVolumeMateriau() %></td>
                                </tr>
                                <tr>
                                  <td>Composant</td>
                                  <td></td>
                                </tr>
                              </tbody>
                            </table>
                          </div>
                        </div>
                        <div class="col-12">
                          <table
                        id="datatable"
                        class="table table-bordered dt-responsive nowrap"
                        style="
                          border-collapse: collapse;
                          border-spacing: 0;
                          width: 100%;
                        "
                      >
                        <thead>
                          <tr>
                            <th>Id</th>
                            <th>Nom</th>
                            <th>Type de materiau</th>
                            <th>Volume</th>
                          </tr>
                        </thead>
                        <tbody>
                          <%
                          for(VComposantMeuble v : vMeuble.getVComposantMeuble()){
                            %>
                            <tr>
                              <td><%= v.getId() %></td>
                              <td><%= v.getNom() %></td>
                              <td><%= v.getNomTypeMateriau() %></td>
                              <td><%= v.getVolume() %></td>
                            </tr>
                            <%
                          }
                          %>
                          
                        </tbody>
                      </table>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- Eto no atao ny page -->
            </div>
          </div>
        </div>
        <%@ include file="/statics/footer.jsp"%>
      </div>
    </div>
    <script src="/template/assets/js/jquery.min.js"></script>
    <script src="/template/assets/js/popper.min.js"></script>
    <script src="/template/assets/js/bootstrap.min.js"></script>
    <script src="/template/assets/js/modernizr.min.js"></script>
    <script src="/template/assets/js/detect.js"></script>
    <script src="/template/assets/js/fastclick.js"></script>
    <script src="/template/assets/js/jquery.slimscroll.js"></script>
    <script src="/template/assets/js/jquery.blockUI.js"></script>
    <script src="/template/assets/js/waves.js"></script>
    <script src="/template/assets/js/jquery.nicescroll.js"></script>
    <script src="/template/assets/js/jquery.scrollTo.min.js"></script>
    <script src="/template/assets/plugins/chart.js/chart.min.js"></script>
    <script src="/template/assets/pages/dashboard.js"></script>
    <script src="/template/assets/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="/template/assets/plugins/datatables/dataTables.bootstrap4.min.js"></script>
    <script src="/template/assets/plugins/datatables/dataTables.responsive.min.js"></script>
    <script src="/template/assets/plugins/datatables/responsive.bootstrap4.min.js"></script>
    <script src="/template/assets/pages/datatables.init.js"></script>
    <script src="/template/assets/js/app.js"></script>
  </body>
</html>
