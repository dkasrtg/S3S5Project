<%@ page isErrorPage="true" %>
<%@ page import="entity.meuble.*" %>
<%@ page import="entity.materiau.*" %>
<%@ page import="java.util.List" %>
<%
List<StyleMeuble> styleMeuble = (List<StyleMeuble>) request.getAttribute("styleMeuble");
List<CategorieMeuble> categorieMeuble = (List<CategorieMeuble>) request.getAttribute("categorieMeuble");
List<LieuMeuble> lieuMeuble = (List<LieuMeuble>) request.getAttribute("lieuMeuble");
List<TypeMateriau> typeMateriau = (List<TypeMateriau>) request.getAttribute("typeMateriau");
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
    <title>Meuble</title>
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
                        <li class="breadcrumb-item active">Meuble</li>
                      </ol>
                    </div>
                    <h4 class="page-title">Meuble</h4>
                  </div>
                </div>
              </div>
              <!-- Test affichage start -->
              <div class="row">
                <div class="col-12">
                  <div class="card">
                    <div class="card-body">
                      <h4 class="mt-0 header-title">Nouveau meuble</h4>
                      <form action="#" method="post">
                      <!-- Test donnees du formulaire <form action="/test_donness" method="post"> -->
                        <div class="row">
                          <div class="col-xl-6">
                            <div class="form-group row">
                              <label
                                for="example-text-input"
                                class="col-sm-2 col-form-label"
                                >Nom</label
                              >
                              <div class="col-sm-10">
                                <input
                                  class="form-control"
                                  type="text"
                                  id="example-text-input"
                                  name="string"
                                />
                              </div>
                            </div>
                            <div class="form-group row">
                              <label
                                for="example-text-input"
                                class="col-sm-2 col-form-label"
                                >Description</label
                              >
                              <div class="col-sm-10">
                                <input
                                  class="form-control"
                                  type="text"
                                  id="example-text-input"
                                  name="string"
                                />
                              </div>
                            </div>
                          </div>
                          <div class="col-xl-6">
                            <div class="form-group row">
                              <label class="col-sm-2 col-form-label"
                                >Categorie</label
                              >
                              <div class="col-sm-10">
                                <select class="form-control" name="id_option_reference">
                                <%
                                for(CategorieMeuble c : categorieMeuble){
                                  %>
                                  <option value="<%= c.getId() %>"><%= c.getNom() %></option>
                                  <%
                                }
                                %>
                                </select>
                              </div>
                            </div>
                            <div class="form-group row">
                              <label class="col-sm-2 col-form-label"
                                >Style</label
                              >
                              <div class="col-sm-10">
                                <select class="form-control" name="id_option_reference">
                                  <%
                                for(StyleMeuble s : styleMeuble){
                                  %>
                                  <option value="<%= s.getId() %>"><%= s.getNom() %></option>
                                  <%
                                }
                                %>
                                </select>
                              </div>
                            </div>
                          </div>
                          <div class="col-xl-12">
                            <div class="form-group row">
                              <label class="col-md-1 my-2 control-label"
                                >Lieux</label
                              >
                              <div class="col-md-11">
                                <%
                                for(LieuMeuble l : lieuMeuble){
                                  %>
                                  <div class="form-check-inline my-2">
                                    <div class="custom-control custom-checkbox">
                                      <input
                                        type="checkbox"
                                        class="custom-control-input"
                                        id="l<%= l.getId() %>"
                                        data-parsley-multiple="groups"
                                        value="<%= l.getId() %>"
                                        name="[]"
                                      />
                                      <label
                                        class="custom-control-label"
                                        for="l<%= l.getId() %>"
                                        ><%= l.getNom() %></label
                                      >
                                    </div>
                                  </div>
                                  <%
                                }
                                %>
                                
                              </div>
                            </div>
                          </div>
                          <div class="col-xl-12">
                            <dt class="col-sm-3">Composants<button style="margin-left: 30px;" type="button" class="btn btn-success" onclick="addNewLine()">+</button> </dt>
                            <div class="row">
                              <div class="col-3">
                                <p>Nom</p>
                              </div>
                              <div class="col-3">
                                <p>Type de materiau</p>
                              </div>
                              <div class="col-3">
                                <p>Volume</p>
                              </div>
                            </div>
                            <div class="row" id="append">
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
                      <h4 class="mt-0 header-title">Liste des meubles</h4>
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
                            <th>Lieux</th>
                            <th>Description</th>
                            <th>Dimensions</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td>1</td>
                            <td>Tabouuret Tabou</td>
                            <td>Chaise</td>
                            <td>Boheme</td>
                            <td>Chambre ; Salon</td>
                            <td>Bla bla </td>
                            <td>2 x 2 x 4</td>
                          </tr>
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
    <script>
      function addNewLine() {
      var newLine = document.createElement('div');
      newLine.className = 'col-12';
      newLine.innerHTML = `
          <div class="row mb-1">
            <div class="col-3">
              <input
                class="form-control"
                type="text"
                id="example-text-input"
                name="string"
              />
            </div>
            <div class="col-3">
              <select class="form-control" name="id_option_reference">
                <%
                for(TypeMateriau t : typeMateriau){
                  %>
                  <option value="<%= t.getId() %>"><%= t.getNom() %></option>
                  <%
                }
                %>
              </select>
            </div>
            <div class="col-3">
              <input
                class="form-control"
                type="text"
                id="example-text-input"
                name="string"
              />
            </div>
            <div class="col-3">
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
    </script>
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
