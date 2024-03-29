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
    <title>Meuble - Statistique vente</title>
    <meta content="Admin Dashboard" name="description" />
    <meta content="Mannatthemes" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="shortcut icon" href="/template/assets/images/favicon.ico" />
    <script src="/js/chart.js-4.4.1/package/dist/chart.umd.js"></script>
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
                        <li class="breadcrumb-item active">
                          Statistique vente
                        </li>
                      </ol>
                    </div>
                    <h4 class="page-title">Statistique vente general</h4>
                  </div>
                </div>
              </div>
              <!-- Meuble - Statistique vente start -->
              <div class="row">
                <div class="col-12">
                  <div class="card">
                    <div class="card-body">
                      <form action="/statistique_vente_general" method="get">
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
                      <h4 class="mt-0 header-title">
                        Statistiques de vente meubles du ${dateDebut} au
                        ${dateFin}
                      </h4>
                      <div class="row">
                        <div class="col-lg-8">
                          <p>En totalite</p>
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
                                <td>Genre</td>
                                <td>Quantite</td>
                                <td>Pourcentage</td>
                              </tr>
                            </thead>
                            <tbody>
                              <c:forEach
                                var="c"
                                items="${venteGlobalParGenres}"
                              >
                                <tr>
                                  <td>${c.nomGenre}</td>
                                  <td>${c.quantite}</td>
                                  <td>${c.pourcentage}</td>
                                </tr>
                              </c:forEach>
                            </tbody>
                          </table>
                        </div>
                        <div class="col-lg-4">
                          <div>
                            <canvas id="myPieChart"></canvas>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- Meuble - Statistique vente end -->
            </div>
          </div>
        </div>
        <%@ include file="/statics/footer.jsp"%>
      </div>
    </div>
    <!-- Pie chart global -->
    <script>
      const data = {
        labels: [
          <c:forEach var="c" items="${venteGlobalParGenres}">
            "${c.nomGenre}",
          </c:forEach>,
        ],
        datasets: [
          {
            data: [
              <c:forEach var="c" items="${venteGlobalParGenres}">
                "${c.pourcentage}",
              </c:forEach>,
            ],
            backgroundColor: ["#3498db", "#e74c3c"],
          },
        ],
      };
      const ctxp = document.getElementById("myPieChart").getContext("2d");
      const myPieChart = new Chart(ctxp, {
        type: "pie",
        data: data,
        options: {
          plugins: {
            legend: {
              position: "right",
            },
          },
        },
      });
    </script>
    <!-- Pie chart global -->
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
    <script src="/template/assets/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="/template/assets/plugins/datatables/dataTables.bootstrap4.min.js"></script>
    <script src="/template/assets/plugins/datatables/dataTables.responsive.min.js"></script>
    <script src="/template/assets/plugins/datatables/responsive.bootstrap4.min.js"></script>
    <script src="/template/assets/pages/datatables.init.js"></script>
    <script src="/template/assets/js/app.js"></script>
  </body>
</html>
