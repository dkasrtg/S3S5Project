<%@ page isErrorPage="true" %>
<%@ page import="entity.meuble.*" %>
<%@ page import="entity.materiau.*" %>
<%@ page import="java.util.List" %>
<%
List<StyleMeuble> styleMeuble = (List<StyleMeuble>) request.getAttribute("styleMeuble");
List<VMateriau> vMateriau = (List<VMateriau>) request.getAttribute("vMateriau");
List<VMateriauPossibleStyleMeuble> vMateriauPossibleStyleMeuble = (List<VMateriauPossibleStyleMeuble>) request.getAttribute("vMateriauPossibleStyleMeuble");
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
    <title>Meuble - Style</title>
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
                        <li class="breadcrumb-item active">Style</li>
                      </ol>
                    </div>
                    <h4 class="page-title">Style</h4>
                  </div>
                </div>
              </div>
              <!-- Test affichage start -->
              <div class="row">
                
                <div class="col-12">
                  <div class="card">
                    <div class="card-body">
                      <h4 class="mt-0 header-title">Assigner un materiau a un style</h4>
                      <form action="/materiau_possible_style_meuble" method="post">
                        <div class="row">
                          <div class="col-xl-6">
                            <div class="form-group row">
                              <label class="col-sm-2 col-form-label"
                                >Materiau</label
                              >
                              <div class="col-sm-10">
                                <select class="form-control" name="id_materiau">
                                  <%
                                  for( VMateriau v : vMateriau){
                                    %>
                                    <option value="<%= v.getId() %>"><%= v.getNom() %></option>
                                    <%
                                  }
                                  %>
                                </select>
                              </div>
                            </div>
                          </div>
                          <div class="col-xl-6">
                            <div class="form-group row">
                              <label class="col-sm-2 col-form-label"
                                >Style</label
                              >
                              <div class="col-sm-10">
                                <select class="form-control" name="id_style_meuble">
                                  <%
                                  for( StyleMeuble s : styleMeuble) {
                                    %>
                                    <option value="<%= s.getId() %>"><%= s.getNom() %></option>
                                    <%
                                  }
                                  %>
                                </select>
                              </div>
                            </div>
                            <div class="form-group row">
                              <div class="col-sm-10"></div>
                              <div class="col-sm-2">
                                <button
                                  type="submit"
                                  class="btn btn-primary waves-effect waves-light"
                                >
                                  Submit
                                </button>
                              </div>
                            </div>
                          </div>
                        </div>
                      </form>
                    </div>
                  </div>                    
                </div>
                <div class="col-12">
                  <div class="card">
                    <div class="card-body">
                      <h4 class="mt-0 header-title">Les materiaux associes aux style</h4>
                      <form action="/materiau_possible_style_meuble" method="get">
                        <div class="row">
                          <div class="col-xl-6">
                            <div class="form-group row">
                              <label class="col-sm-2 col-form-label"
                                >Style</label
                              >
                              <div class="col-sm-10">
                                <select class="form-control" name="id_style_meuble">
                                  <%
                                  for( StyleMeuble s : styleMeuble) {
                                    %>
                                    <option value="<%= s.getId() %>"><%= s.getNom() %></option>
                                    <%
                                  }
                                  %>
                                </select>
                              </div>
                            </div>
                          </div>
                          <div class="col-xl-6">
                            <div class="form-group row">
                              <div class="col-sm-2">
                                <button
                                  type="submit"
                                  class="btn btn-primary waves-effect waves-light"
                                >
                                  Voir
                                </button>
                              </div>
                            </div>
                          </div>
                        </div>
                      </form>
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
                            <th>Type</th>
                          </tr>
                        </thead>
                        <tbody>
                          <%
                          for (VMateriauPossibleStyleMeuble v : vMateriauPossibleStyleMeuble) {
                            %>
                            <tr>
                              <td><%= v.getIdMateriau()  %></td>
                              <td><%= v.getNomMateriau() %></td>
                              <td><%= v.getNomTypeMateriau() %></td>
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
              <!-- Test affichage end -->
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
