<%@ page isErrorPage="true" %>
<%@ page import="entity.materiau.*" %>
<%@ page import="java.util.List" %>
<%
List<DimensionMateriau> dimensionMateriau = (List<DimensionMateriau>) request.getAttribute("dimensionMateriau");
List<VStockageMateriau> vStockageMateriau = (List<VStockageMateriau>) request.getAttribute("vStockageMateriau");
List<VMateriau> vMateriau = (List<VMateriau>) request.getAttribute("vMateriau");
List<UniteMateriau> uniteMateriau = (List<UniteMateriau>) request.getAttribute("uniteMateriau");
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
    <title>Materiau - Stockage</title>
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
                          <a href="#">Materiau</a>
                        </li>
                        <li class="breadcrumb-item active">Stockage</li>
                      </ol>
                    </div>
                    <h4 class="page-title">Stockage</h4>
                  </div>
                </div>
              </div>
              <!-- Test affichage start -->
              <div class="row">
                <div class="col-12">
                  <div class="card">
                    <div class="card-body">
                      <h4 class="mt-0 header-title">Nouvelle stockage</h4>
                      <form action="/stockage_materiau" method="post">
                        <div class="row">
                          <div class="col-xl-6">
                            <div class="form-group row">
                              <label class="col-sm-2 col-form-label"
                                >Materiau</label
                              >
                              <div class="col-sm-10">
                                <select class="form-control" name="id_materiau">
                                  <%
                                  for(VMateriau v : vMateriau){
                                    %>
                                    <option value="<%= v.getId() %>"><%= v.getNom() %></option>
                                    <%
                                  }
                                  %>
                                </select>
                              </div>
                            </div>
                            <div class="form-group row">
                              <label
                                for="example-number-input"
                                class="col-sm-2 col-form-label"
                                >Quantite</label
                              >
                              <div class="col-sm-10">
                                <input
                                  class="form-control"
                                  type="text"
                                  id="example-number-input"
                                  name="quantite_stockage"
                                />
                              </div>
                            </div>
                            <div class="form-group row">
                              <label
                                for="example-number-input"
                                class="col-sm-2 col-form-label"
                                >Prix unitaire</label
                              >
                              <div class="col-sm-10">
                                <input
                                  class="form-control"
                                  type="text"
                                  id="example-number-input"
                                  name="prix_unitaire"
                                />
                              </div>
                            </div>
                            <div class="form-group row">
                              <label class="col-sm-2 col-form-label"
                                >Unite</label
                              >
                              <div class="col-sm-10">
                                <select class="form-control" name="id_unite_materiau">
                                  <%
                                  for (UniteMateriau u : uniteMateriau){
                                    %>
                                    <option value="<%= u.getId() %>"><%= u.getNom() %></option>
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
                                >Dimension</label
                              >
                              <div class="col-sm-10">
                                <select class="form-control" name="id_dimension_materiau">
                                  <%
                                  for (DimensionMateriau d : dimensionMateriau){
                                    %>
                                    <option value="<%= d.getId() %>"><%= d.getLongueur() %> x <%= d.getLargeur() %> x <%= d.getHauteur() %></option>
                                    <%
                                  }
                                  %>
                                </select>
                              </div>
                            </div>
                            <div class="form-group row">
                              <label
                                for="example-date-input"
                                class="col-sm-2 col-form-label"
                                >Date</label
                              >
                              <div class="col-sm-10">
                                <input
                                  class="form-control"
                                  type="date"
                                  id="example-date-input"
                                  name="date_stockage"
                                />
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
                      <h4 class="mt-0 header-title">Liste des stockages</h4>
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
                            <th>Date</th>
                            <th>Materiau</th>
                            <th>Type</th>
                            <th>Unite</th>
                            <th>Dimension</th>
                            <th>Quantite</th>
                            <th>Prix Unitaire</th>
                            <th>Prix Total</th>
                          </tr>
                        </thead>
                        <tbody>
                          <%
                          for (VStockageMateriau v : vStockageMateriau){
                            %>
                            <tr>
                              <td><%= v.getId() %></td>
                              <td><%= v.getDateStockage() %></td>
                              <td><%= v.getNomMateriau() %></td>
                              <td><%= v.getNomTypeMateriau() %></td>
                              <td><%= v.getNomUniteMateriau() %></td>
                              <td><%= v.getLongueur() %> x <%= v.getLargeur() %> x <%= v.getHauteur() %></td>
                              <td><%= v.getQuantiteStockage() %></td>
                              <td><%= v.getPrixUnitaire() %></td>
                              <td><%= v.getPrixTotal() %></td>
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
