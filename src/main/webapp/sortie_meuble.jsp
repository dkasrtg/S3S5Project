<!-- <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui"
    />
    <title>Meuble - Sortie</title>
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
                        <li class="breadcrumb-item active">Sortie</li>
                      </ol>
                    </div>
                    <h4 class="page-title">Sortie meuble</h4>
                  </div>
                </div>
              </div>
              <!-- Test affichage start -->
              <div class="row">
                <div class="col-12">
                  <div class="card">
                    <div class="card-body">
                      <h4 class="mt-0 header-title">Nouvelle sortie</h4>
                      <form action="/sortie_meuble" method="post">
                        <div class="row">
                          <div class="col-xl-6">
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label"
                                  >Meuble</label
                                >
                                <div class="col-sm-10">
                                  <select class="form-control" name="id_meuble">
                                      <c:forEach var="c" items="${vMeubles}">
                                        <option value="${c.id}">${c.nom}</option>
                                      </c:forEach>
                                  </select>
                                </div>
                              </div>  
                              <div class="form-group row">
                                <label class="col-sm-2 col-form-label"
                                  >Taille</label
                                >
                                <div class="col-sm-10">
                                  <select class="form-control" name="id_taille_meuble">
                                    <c:forEach var="c" items="${tailleMeubles}">
                                        <option value="${c.id}">${c.nom}</option>
                                      </c:forEach>
                                  </select>
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
                                    name="description"
                                  />
                                </div>
                              </div>
                          </div>
                          <div class="col-xl-6">
                            <div class="form-group row">
                              <label
                                for="example-text-input"
                                class="col-sm-2 col-form-label"
                                >Quantite</label
                              >
                              <div class="col-sm-10">
                                <input
                                  class="form-control"
                                  type="text"
                                  id="example-text-input"
                                  name="quantite"
                                />
                              </div>
                            </div>
                            <div class="form-group row">
                                <label
                                  for="example-text-input"
                                  class="col-sm-2 col-form-label"
                                  >Date</label
                                >
                                <div class="col-sm-10">
                                  <input
                                    class="form-control"
                                    type="datetime-local"
                                    id="example-text-input"
                                    name="date_sortie"
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
                      <form action="/sortie_meuble" method="get">
                        <div class="row">
                          <div class="col-xl-6">
                            <div class="form-group row">
                              <label
                                for="example-text-input"
                                class="col-sm-2 col-form-label"
                                >Date debut</label
                              >
                              <div class="col-sm-10">
                                <input
                                  class="form-control"
                                  type="datetime-local"
                                  id="example-text-input"
                                  name="date_debut"
                                />
                              </div>
                            </div>
                          </div>
                          <div class="col-xl-6">
                            <div class="form-group row">
                              <label
                                for="example-text-input"
                                class="col-sm-2 col-form-label"
                                >Date fin</label
                              >
                              <div class="col-sm-10">
                                <input
                                  class="form-control"
                                  type="datetime-local"
                                  id="example-text-input"
                                  name="date_fin"
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
                      <h4 class="mt-0 header-title">Sortie de meubles du ${dateDebut} au ${dateFin}</h4>
                      <table
                        id="datatable"
                        class="table table-bordered dt-responsive"
                        style="
                          border-collapse: collapse;
                          border-spacing: 0;
                          width: 100%;
                        "
                      >
                      <thead>
                        <tr>
                          <th>Id</th>
                          <th>IdMouvementMere</th>
                          <th>Date</th>
                          <th>Description</th>
                          <th>Meuble</th>
                          <th>Taille</th>
                          <th>Categorie</th>
                          <th>Style</th>
                          <th>Quantite</th>
                          <th>Prix unitaire</th>
                          <th>Prix total</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach var="v" items="${vMouvementMeubles}">
                            <tr>
                                <td>${v.id}</td>
                                <td>${v.idMouvementMere}</td>
                                <td>${v.dateMouvement}</td>
                                <td>${v.description}</td>
                                <td>${v.nomMeuble}</td>
                                <td>${v.nomTailleMeuble}</td>
                                <td>${v.nomCategorieMeuble}</td>
                                <td>${v.nomStyleMeuble}</td>
                                <td>${v.quantite}</td>
                                <td>${v.prixUnitaire}</td>
                                <td>${v.prixTotal}</td>
                            </tr>
                        </c:forEach>
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
