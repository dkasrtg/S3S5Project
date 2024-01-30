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
    <title>Meuble - Detail vente</title>
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
                        <li class="breadcrumb-item active">Detail vente</li>
                      </ol>
                    </div>
                    <h4 class="page-title">Detail vente</h4>
                  </div>
                </div>
              </div>
              <!-- Test affichage start -->
              <div class="row">
                <div class="col-12">
                  <div class="card">
                    <div class="card-body">
                      <h4 class="mt-0 header-title">Detail du vente numero ${vVenteMeuble.id}</h4>
                      <div class="row">
                        <div class="col-6">
                          <div class="table-responsive">
                            <table class="table mb-0 table-centered">
                              <tbody>
                                <tr>
                                  <td>Date :</td>
                                  <td>${vVenteMeuble.dateVente}</td>
                                </tr>
                                <tr>
                                  <td>Client :</td>
                                  <td>${vVenteMeuble.nomClient} ${vVenteMeuble.prenomClient} </td>
                                </tr>
                                <tr>
                                  <td>Prix total :</td>
                                  <td>${vVenteMeuble.prixTotal}</td>
                                </tr>
                                <tr>
                                  <td></td>
                                  <td></td>
                                </tr>
                              </tbody>
                            </table>
                          </div>
                        </div>
                      </div>
                      <div class="table-responsive">
                        <table class="table table-bordered mb-0 table-centered">
                          <thead>
                            <tr>
                              <th>Meuble</th>
                              <th>Taille</th>
                              <th>Quantite</th>
                              <th>Prix unitaire</th>
                              <th>Prix total</th>
                            </tr>
                          </thead>
                          <tbody>
                            <c:forEach var="c" items="${vDetailVenteMeubles}">
                              <tr>
                                <td>${c.nomMeuble}</td>
                                <td>${c.nomTailleMeuble}</td>
                                <td>${c.quantite}</td>
                                <td>${c.prixUnitaire}</td>
                                <td>${c.prixTotal}</td>
                              </tr>
                            </c:forEach>
                          </tbody>
                        </table>
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
    <script>
      function addNewLine() {
        var newLine = document.createElement('div');
        newLine.className = 'col-12';
        newLine.innerHTML = `
            <div class="row mb-1">
              <div class="col-4">
                <select class="form-control" name="id_formule_meuble[]">
                  <c:forEach var="c" items="${vFormuleMeubleComplets}">
                      <option value="${c.id}">${c.nomMeuble}  - ${c.nomTailleMeuble}</option>
                  </c:forEach>
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
