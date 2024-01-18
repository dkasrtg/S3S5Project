<%@ page isErrorPage="true" %>
<%@ page import="entity.meuble.*" %>
<%@ page import="entity.materiau.*" %>
<%@ page import="entity.employe.*" %>
<%@ page import="java.util.List" %>
<%
VMeuble vMeuble = (VMeuble) request.getAttribute("vMeuble");  
List<VMateriauPossibleStyleMeuble> vMateriauPossibleStyleMeuble = (List<VMateriauPossibleStyleMeuble>) request.getAttribute("vMateriauPossibleStyleMeuble");
List<VMateriau> vMateriaus = (List<VMateriau>) request.getAttribute("vMateriaus");  
List<TailleMeuble> tailleMeuble = (List<TailleMeuble>) request.getAttribute("tailleMeuble");  
List<Employe> employe = (List<Employe>) request.getAttribute("employe");
List<VFormuleMeuble> vFormuleMeubles = (List<VFormuleMeuble>) request.getAttribute("vFormuleMeubles");
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
    <title>Meuble - Formule</title>
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
                      <h4 class="mt-0 header-title">Nouvelle formule pour le meuble <i>"<%= vMeuble.getNom() %>"</i></h4>
                      <form action="/nouvelle_formule_meuble" method="post">
                        <input type="hidden" name="id_meuble" value="<%= vMeuble.getId() %>">
                        <div class="row">
                          <div class="col-xl-6">
                            <div class="form-group row">
                              <label class="col-sm-2 col-form-label"
                                >Taille</label
                              >
                              <div class="col-sm-10">
                                <select class="form-control" name="id_taille_meuble">
                                  <%
                                  for (TailleMeuble t: tailleMeuble){
                                    %>
                                    <option value="<%= t.getId() %>"><%= t.getNom() %></option>
                                    <%
                                  }
                                  %>
                                </select>
                              </div>
                            </div>
                          </div>
                          <div class="col-xl-12">
                            <dt class="col-sm-3">Compositions<button style="margin-left: 30px;" type="button" class="btn btn-success" onclick="addNewLine()">+</button> </dt>
                            <div class="row">
                              <div class="col-4">
                                <p>Materiau</p>
                              </div>
                              <div class="col-4">
                                <p>Quantite</p>
                              </div>
                            </div>
                            <div class="row" id="append">
                            </div>
                          </div>
                          <div class="col-xl-12" style="margin-top: 10px;">
                            <dt class="col-sm-3">Employes utilises<button style="margin-left: 30px;" type="button" class="btn btn-success" onclick="addNewLine2()">+</button> </dt>
                            <div class="row">
                              <div class="col-4">
                                <p>Employe</p>
                              </div>
                              <div class="col-4">
                                <p>Nombre</p>
                              </div>
                              <div class="col-4">
                                <p>Duree</p>
                              </div>
                            </div>
                            <div class="row" id="append2">
                            </div>
                          </div>
                          <div class="col-xl-6"></div>
                          <div class="col-xl-6">
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
                      <h4 class="mt-0 header-title">Les formules du meuble <i>"<%= vMeuble.getNom() %>"</i></h4>
                      <%
                      for (VFormuleMeuble v: vFormuleMeubles){
                        %>
                        <div class="row">
                          <div class="col-12">
                            <h6>Taille <%= v.getNomTailleMeuble() %></h6>
                          </div>
                          <div class="col-5">
                            <table
                              class="table table-bordered dt-responsive nowrap"
                              style="
                                border-collapse: collapse;
                                border-spacing: 0;
                                width: 100%;
                              "
                            >
                              <thead>
                                <tr>
                                  <th>Materiau</th>
                                  <th>Quantite</th>
                                </tr>
                              </thead>
                              <tbody>
                                <%
                                for (VDetailFormuleMeuble vdfm: v.getvDetailFormuleMeubles()){
                                  %>
                                  <tr>
                                    <td><%= vdfm.getNomMateriau() %></td>
                                    <td><%= vdfm.getQuantite() %></td>
                                  </tr>
                                  <%
                                }
                                %>
                              </tbody>
                            </table>
                          </div>
                          <div class="col-7">
                            <table
                              class="table table-bordered dt-responsive nowrap"
                              style="
                                border-collapse: collapse;
                                border-spacing: 0;
                                width: 100%;
                              "
                            >
                              <thead>
                                <tr>
                                  <th>Employe</th>
                                  <th>Nombre</th>
                                  <th>Duree</th>
                                </tr>
                              </thead>
                              <tbody>
                                <%
                                for (VDetailEmployeMeuble vdem: v.getvDetailEmployeMeubles()){
                                  %>
                                  <tr>
                                    <td><%= vdem.getNomEmploye() %></td>
                                    <td><%= vdem.getNombre() %></td>
                                    <td><%= vdem.getDuree() %></td>
                                  </tr>
                                  <%
                                }
                                %>
                              </tbody>
                            </table>
                          </div>
                        </div>
                        <%
                      }
                      %>
                      
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
    <script>
      function addNewLine() {
      var newLine = document.createElement('div');
      newLine.className = 'col-12';
      newLine.innerHTML = `
          <div class="row mb-1">
            <div class="col-4">
              <select class="form-control" name="id_materiau[]">
                <%
                for(VMateriauPossibleStyleMeuble v : vMateriauPossibleStyleMeuble){
                  %>
                  <option value="<%= v.getIdMateriau() %>"><%= v.getNomMateriau() %></option>
                  <%
                }
                %>
              </select>
            </div>
            <div class="col-4">
              <input
                class="form-control"
                type="text"
                id="example-text-input"
                name="quantite[]"
              />
            </div>
            <div class="col-4">
                <button class="btn btn-danger" type="button">X</button>
            </div>
          </div>
      `;
        document.getElementById('append').appendChild(newLine);
        var closeButton = newLine.querySelector('.btn-danger');
        closeButton.addEventListener('click', function() {
            this.closest('.col-12').remove();
        });
    }
    addNewLine();
    
    function addNewLine2() {
      var newLine = document.createElement('div');
      newLine.className = 'col-12';
      newLine.innerHTML = `
          <div class="row mb-1">
            <div class="col-3">
              <select class="form-control" name="id_employe[]">
                <%
                for(Employe e : employe){
                  %>
                  <option value="<%= e.getId() %>"><%= e.getNom() %></option>
                  <%
                }
                %>
              </select>
            </div>
            <div class="col-3">
              <input
                class="form-control"
                type="number"
                id="example-text-input"
                name="nombre[]"
              />
            </div>
            <div class="col-3">
              <input
                class="form-control"
                type="text"
                id="example-text-input"
                name="duree[]"
              />
            </div>
            <div class="col-3">
                <button class="btn btn-danger" type="button">X</button>
            </div>
          </div>
      `;
        document.getElementById('append2').appendChild(newLine);
        var closeButton = newLine.querySelector('.btn-danger');
        closeButton.addEventListener('click', function() {
            this.closest('.col-12').remove();
        });
    }
    addNewLine2();
    </script>
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
