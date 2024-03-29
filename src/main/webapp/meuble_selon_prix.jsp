<%@ page isErrorPage="true" %>
<%@ page import="entity.meuble.*" %>
<%@ page import="entity.materiau.*" %>
<%@ page import="java.util.List" %>
<%
String prixMin = (String) request.getAttribute("prixMin");
String prixMax = (String) request.getAttribute("prixMax");
List<VMeuble> vMeuble = (List<VMeuble>) request.getAttribute("vMeuble");  
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
    <title>Meuble - Prix</title>
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
                        <li class="breadcrumb-item active">Prix</li>
                      </ol>
                    </div>
                    <h4 class="page-title">Prix</h4>
                  </div>
                </div>
              </div>
              <!-- Test affichage start -->
              <div class="row">
                <div class="col-12">
                  <div class="card">
                    <div class="card-body">
                      <form action="/meuble_selon_prix" method="get">
                        <div class="row">
                        <div class="col-xl-6">
                          <div class="form-group row">
                            <label class="col-sm-2 col-form-label"
                              >Prix Min</label
                            >
                            <div class="col-sm-10">
                              <input
                                  class="form-control"
                                  type="text"
                                  id="example-date-input"
                                  name="prix_min"
                                />
                            </div>
                          </div>
                          <div class="form-group row">
                            
                              <button style="margin-left: 15px;"
                                type="submit"
                                class="btn btn-primary waves-effect waves-light"
                              >
                                OK
                              </button>
                          </div>
                        </div>
                        <div class="col-xl-6">
                          <div class="form-group row">
                            <label class="col-sm-2 col-form-label"
                              >Prix Max</label
                            >
                            <div class="col-sm-10">
                              <input
                                  class="form-control"
                                  type="text"
                                  id="example-date-input"
                                  name="prix_max"
                                />
                            </div>
                          </div>
                        </div>
                      </div>
                      </form>
                      <h4 class="mt-0 header-title">Les meubles compris entre <%= prixMin %> et <%= prixMax %></h4>
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
                            <th>Categorie</th>
                            <th>Style</th>
                            <th>Description</th>
                            <th>Taille</th>
                            <th>Prix total</th>
                          </tr>
                        </thead>
                        <tbody>
                          <%
                          for(VMeuble v : vMeuble) {
                            %>
                            <tr>
                              <td><%= v.getId() %></td>
                              <td><%= v.getNom() %></td>
                              <td><%= v.getNomCategorieMeuble() %></td>
                              <td><%= v.getNomStyleMeuble() %></td>
                              <td><%= v.getDescription() %></td>
                              <td><%= v.getNomTailleMeuble() %></td>
                              <td><%= v.getPrixUnitaire() %></td>
                            </tr>
                            <%
                          }
                          %>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
                <!-- end col -->
              </div>
              <!-- Test affichage end -->
            </div>
          </div>
        </div>
        <%@ include file="/statics/footer.jsp"%>
      </div>
    </div>
    <script src="/js/error.js"></script>
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
