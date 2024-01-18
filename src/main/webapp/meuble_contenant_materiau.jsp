<%@ page isErrorPage="true" %>
<%@ page import="entity.meuble.*" %>
<%@ page import="entity.materiau.*" %>
<%@ page import="java.util.List" %>
<%
List<VMateriau> vMateriaus = (List<VMateriau>) request.getAttribute("vMateriaus");  
VMateriau vMateriau = (VMateriau) request.getAttribute("vMateriau");  
List<MeubleContenantMateriau> meubleContenantMateriau = (List<MeubleContenantMateriau>) request.getAttribute("meubleContenantMateriau");  
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
    <title>Meuble - Contenant materiau</title>
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
                        <li class="breadcrumb-item active">Formule</li>
                      </ol>
                    </div>
                    <h4 class="page-title">Formule</h4>
                  </div>
                </div>
              </div>
              <!-- Test affichage start -->
              <div class="row">
                <div class="col-12">
                  <div class="card">
                    <div class="card-body">
                      <form action="/meuble_contenant_materiau" method="get">
                        <div class="row">
                        <div class="col-xl-6">
                          <div class="form-group row">
                            <label class="col-sm-2 col-form-label"
                              >Materiau</label
                            >
                            <div class="col-sm-10">
                              <select class="form-control" name="id_materiau">
                                <%
                                for(VMateriau v : vMateriaus){
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
                            
                              <button
                                type="submit"
                                class="btn btn-primary waves-effect waves-light"
                              >
                                OK
                              </button>
                          </div>
                        </div>
                      </div>
                      </form>
                      <h4 class="mt-0 header-title">Les meubles contentant le materiau <i>"<%= vMateriau.getNom() %>"</i></h4>
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
                            <th>Taille</th>
                            <th>Quantite</th>
                          </tr>
                        </thead>
                        <tbody>
                          <%
                          for(MeubleContenantMateriau v : meubleContenantMateriau) {
                            %>
                            <tr>
                              <td><%= v.getId() %></td>
                              <td><%= v.getNomMeuble() %></td>
                              <td><%= v.getNomCategorieMeuble() %></td>
                              <td><%= v.getNomStyleMeuble() %></td>
                              <td><%= v.getNomTailleMeuble() %></td>
                              <td><%= v.getQuantite() %></td>
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
