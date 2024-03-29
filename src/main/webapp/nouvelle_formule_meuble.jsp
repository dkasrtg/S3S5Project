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
                      <h4 class="mt-0 header-title">Nouvelle formule pour le meuble <i>"${vMeuble.nom}"</i></h4>
                      <form action="/nouvelle_formule_meuble" method="post">
                        <input type="hidden" name="id_meuble" value="${vMeuble.id}">
                        <div class="row">
                          <div class="col-xl-6">
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
                              <div class="col-3">
                                <p>Poste</p>
                              </div>
                              <div class="col-3">
                                <p>Niveau</p>
                              </div>
                              <div class="col-2">
                                <p>Nombre</p>
                              </div>
                              <div class="col-2">
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
                      <h4 class="mt-0 header-title">Les formules du meuble <i>"${vMeuble.nom}"</i></h4>
                      <c:forEach var="c" items="${vFormuleMeubles}">
                        <div class="row">
                          <div class="col-12">
                            <h6> - Taille ${c.nomTailleMeuble}</h6>
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
                                  <c:forEach var="d" items="${c.vDetailFormuleMeubles}">
                                  <tr>
                                    <td>${d.nomMateriau}</td>
                                    <td>${d.quantite}</td>
                                  </tr>
                                </c:forEach>
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
                                  <th>Poste</th>
                                  <th>Niveau</th>
                                  <th>Nombre</th>
                                  <th>Duree</th>
                                </tr>
                              </thead>
                              <tbody>
                                <c:forEach var="d" items="${c.vDetailEmployeMeubles}">
                                  <tr>
                                    <td>${d.nomPoste}</td>
                                    <td>${d.nomNiveau}</td>
                                    <td>${d.nombre}</td>
                                    <td>${d.duree}</td>
                                  </tr>
                                </c:forEach>
                              </tbody>
                            </table>
                          </div>
                        </div>
                    </c:forEach>
                      
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
                  <c:forEach var="c" items="${vMateriauPossibleStyleMeubles}">
                  <option value="${c.idMateriau}">${c.nomMateriau}</option>
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
    
    function addNewLine2() {
      var newLine = document.createElement('div');
      newLine.className = 'col-12';
      newLine.innerHTML = `
          <div class="row mb-1">
            <div class="col-3">
              <select class="form-control" name="id_poste[]">
                <c:forEach var="c" items="${postes}">
                  <option value="${c.id}">${c.nom}</option>
                  </c:forEach>
              </select>
            </div>
            <div class="col-3">
              <select class="form-control" name="id_niveau[]">
                <c:forEach var="c" items="${niveaus}">
                  <option value="${c.id}">${c.nom}</option>
                  </c:forEach>
              </select>
            </div>
            <div class="col-2">
              <input
                class="form-control"
                type="number"
                id="example-text-input"
                name="nombre[]"
              />
            </div>
            <div class="col-2">
              <input
                class="form-control"
                type="text"
                id="example-text-input"
                name="duree[]"
              />
            </div>
            <div class="col-1">
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
