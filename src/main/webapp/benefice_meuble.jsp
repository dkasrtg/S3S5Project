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
    <title>Meuble - Benefice</title>
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
                        <li class="breadcrumb-item active">Benefice</li>
                      </ol>
                    </div>
                    <h4 class="page-title">Benefice</h4>
                  </div>
                </div>
              </div>
              <!-- Test affichage start -->
              <div class="row">
                <div class="col-12">
                  <div class="card">
                    <div class="card-body">
                        <form action="/benefice_meuble" method="get">
                            <div class="row">
                              <div class="col-xl-6">
                                <div class="form-group row">
                                  <label
                                    for="example-text-input"
                                    class="col-sm-2 col-form-label"
                                    >Minimum</label
                                  >
                                  <div class="col-sm-10">
                                    <input
                                      class="form-control"
                                      type="text"
                                      id="example-text-input"
                                      name="min"
                                    />
                                  </div>
                                </div>
                                
                              </div>
                              <div class="col-xl-6">
                                <div class="form-group row">
                                  <label
                                    for="example-text-input"
                                    class="col-sm-2 col-form-label"
                                    >Maximum</label
                                  >
                                  <div class="col-sm-10">
                                    <input
                                      class="form-control"
                                      type="nom"
                                      id="example-text-input"
                                      name="max"
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
                      <h4 class="mt-0 header-title">Les meubles dont le benefice est compris entre ${min} et ${max}</h4>
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
                            <th>Meuble</th>
                            <th>Taille</th>
                            <th>Prix de vente</th>
                            <th>Total materiaux (SELON PRIX DES RESTANTS)</th>
                            <th>Total salaires (SELON BASE TAUX HORAIRE)</th>
                            <th>Prix de revient</th>
                            <th>Benefice</th>
                          </tr>
                        </thead>
                        <tbody>
                         <c:forEach var="c" items="${vBeneficeMeubles}">
                          <tr>
                            <td>${c.nomMeuble}</td>
                            <td>${c.nomTailleMeuble}</td>
                            <td>${c.prixDeVente}</td>
                            <td>${c.prixTotalMateriau}</td>
                            <td>${c.prixTotalSalaire}</td>
                            <td>${c.prixDeRevient}</td>
                            <td>${c.benefice}</td>
                          </tr>
                         </c:forEach>
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
