<%@ page isErrorPage="true" %>
<%@ page import="entity.materiau.*" %>
<%@ page import="entity.meuble.*" %>
<%@ page import="java.util.List" %>
<%
List<VMateriau> vMateriau = (List<VMateriau>) request.getAttribute("vMateriau");
List<DimensionMateriau> dimensionMateriau = (List<DimensionMateriau>) request.getAttribute("dimensionMateriau");
List<UniteMateriau> uniteMateriau = (List<UniteMateriau>) request.getAttribute("uniteMateriau");
List<VComposantMeuble> vComposantMeuble = (List<VComposantMeuble>) request.getAttribute("vComposantMeuble");
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
    <title>Meuble - Fabrication</title>
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
                        <li class="breadcrumb-item active">Fabrication</li>
                      </ol>
                    </div>
                    <h4 class="page-title">Fabrication</h4>
                  </div>
                </div>
              </div>
              <!-- Test affichage start -->
              <div class="row">
                <div class="col-12">
                  <div class="card">
                    <div class="card-body">
                      <div class="row">
                        <div class="col-10">
                          <h4 class="mt-0 header-title">Choix des materiaux</h4>
                        </div>
                        <div class="col-2">
                          <form action="/fabrication_meuble" method="get">
                            <button
                                type="submit"
                                class="btn btn-danger waves-effect waves-light"
                              >
                              Precedent
                            </button>
                          </form>
                        </div>
                      </div>
                        <div class="row">
                          <div class="col-xl-12">
                            <div class="row">
                              <div class="col-2">
                                <p>Composant</p>
                              </div>
                              <div class="col-2">
                                <p>Type de materiau</p>
                              </div>
                              <div class="col-2">
                                <p>Materiau</p>
                              </div>
                              <div class="col-2">
                                <p>Unite</p>
                              </div>
                              <div class="col-2">
                                <p>Dimension</p>
                              </div>
                            </div>
                            <form action="/verification_fabrication_meuble" method="post">
                              <%
                              for (VComposantMeuble v : vComposantMeuble){
                                %>
                                <input type="hidden" name="id_composant[]" value="<%= v.getId() %>">
                                <div class="row mb-4">
                                  <div class="col-2">
                                    <input
                                      class="form-control"
                                      type="text"
                                      id="example-text-input"
                                      name="id_composant"
                                      value="<%= v.getNom() %>"
                                      disabled
                                    />
                                  </div>
                                  <div class="col-2">
                                      <input
                                          class="form-control"
                                          type="text"
                                          id="example-text-input"
                                          name="string"
                                          value="<%= v.getNomTypeMateriau() %>"
                                          disabled
                                        />
                                  </div>
                                  <div class="col-2">
                                    <select class="form-control" name="id_materiau[]">
                                      <%
                                      for (VMateriau vm : vMateriau) {
                                        %>
                                        <option value="<%= vm.getId() %>"><%= vm.getNom() %></option>
                                        <%
                                      }
                                      %>
                                    </select>
                                  </div>
                                  <div class="col-2">
                                    <select class="form-control" name="id_unite_materiau[]">
                                      <%
                                      for (UniteMateriau um : uniteMateriau ){
                                        %>
                                        <option value="<%= um.getId() %>"><%= um.getNom() %></option>
                                        <%
                                      }
                                      %>
                                    </select>
                                  </div>
                                  <div class="col-2">
                                    <select class="form-control" name="id_dimension_materiau[]">
                                      <%
                                      for (DimensionMateriau dm : dimensionMateriau ){
                                        %>
                                        <option value="<%= dm.getId() %>"><%= dm.getLongueur() %> x <%= dm.getLongueur() %> x <%= dm.getLongueur() %></option>
                                        <%
                                      }
                                      %>
                                    </select>
                                  </div>
                                </div>
                                <%
                              }
                              %>
                              <div class="row">
                                <div class="col-11"></div>
                                <div class="col-1">
                                  <button
                                    type="submit"
                                    class="btn btn-primary waves-effect waves-light"
                                  >
                                    Suivant
                                  </button>                                
                              </div>
                              </div>
                            </form>
                          </div>
                        </div>
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