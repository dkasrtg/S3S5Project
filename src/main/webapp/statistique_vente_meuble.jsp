<!-- <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> -->

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
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
                        <li class="breadcrumb-item active">
                          Statistique vente
                        </li>
                      </ol>
                    </div>
                    <h4 class="page-title">Statistique vente</h4>
                  </div>
                </div>
              </div>
              <!-- Meuble - Statistique vente start -->
              <div class="row">
                <div class="col-12">
                  <div class="card">
                    <div class="card-body">
                      <h4 class="mt-0 header-title">
                        Statistiques de vente meubles
                      </h4>
                      <div class="row">
                        <div class="col-lg-6 ml-1">
                          <p>En totalite</p>
                          <table
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
                                <td>Pourcentage</td>
                              </tr>
                            </thead>
                            <tbody>
                              <c:forEach var="c" items="${vVenteGlobalGenres}">
                                <tr>
                                  <td>${c.genre}</td>
                                  <td>${c.quantite}</td>
                                </tr>
                              </c:forEach>
                            </tbody>
                          </table>
                        </div>
                        <div class="col-lg-4">
                          <div style="height: 200px;">
                            <canvas
                            id="myPieChart"
                            width="50"
                            height="50"
                          ></canvas>
                          </div>
                        </div>
                      </div>
                      <p>Par produits</p>
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
                            <th>Pourcentage Homme</th>
                            <th>Porcentage Femme</th>
                            <th>Representation</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach var="c" items="${vFormuleMeubleComplets}">
                            <tr>
                              <td>${c.nomMeuble}</td>
                              <td>${c.nomTailleMeuble}</td>
                              <c:forEach
                                var="d"
                                items="${c.vTotalVenteProduitGenres}"
                              >
                                <td>${d.quantite}</td>
                              </c:forEach>
                              <td>
                                <div style="height:100px">
                                  <canvas
                                  id="mc${c.id}"
                                  width="10"
                                  height="10"
                                ></canvas>
                                </div>
                                
                              </td>
                            </tr>
                          </c:forEach>
                        </tbody>
                      </table>
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
    <script>
      const data = {
        labels: [
          <c:forEach var="c" items="${vVenteGlobalGenres}">
            "${c.genre}",
          </c:forEach>,
        ],
        datasets: [
          {
            data: [
              <c:forEach var="c" items="${vVenteGlobalGenres}">
                "${c.quantite}",
              </c:forEach>,
            ],
            backgroundColor: ["#FF6384", "#36A2EB", "#FFCE56"],
          },
        ],
      };
      const ctxp = document.getElementById("myPieChart").getContext("2d");
      const myPieChart = new Chart(ctxp, {
        type: "pie",
        data: data,
        options: {
          
        },
      });
    </script>
    <c:forEach var="c" items="${vFormuleMeubleComplets}">
      <script>
        const ${"data"}${c.id} = {
          labels: [
          <c:forEach
            var="d"
            items="${c.vTotalVenteProduitGenres}"
          >
            "${d.genre}",
          </c:forEach>
          ],
          datasets: [
            {
              data: [
              <c:forEach
                var="d"
                items="${c.vTotalVenteProduitGenres}"
              >
                ${d.quantite},
              </c:forEach>
              ],
              backgroundColor: ["#FF6384", "#36A2EB", "#FFCE56"],
            },
          ],
        };
        const ${"ctx"}${c.id} = document.getElementById("mc${c.id}").getContext("2d");
        const ${"mc"}${c.id} = new Chart(${"ctx"}${c.id}, {
          type: "pie",
          data: ${"data"}${c.id},
          options: {
            width:5,
            height:5
          },
        });
      </script>
    </c:forEach>
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
