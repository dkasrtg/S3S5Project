<%@ page isErrorPage="true" %>
<%@ page import="entity.reference.OptionReference" %>
<%@ page import="entity.reference.VReference" %>
<%@ page import="java.util.List" %>
<%
List<OptionReference> optionReferences = (List<OptionReference>) request.getAttribute("optionReferences");
List<VReference> vReferences = (List<VReference>) request.getAttribute("vReferences");
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
    <title>Reference</title>
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
                          <a href="#">Reference</a>
                        </li>
                        <li class="breadcrumb-item active">Reference</li>
                      </ol>
                    </div>
                    <h4 class="page-title">Reference</h4>
                  </div>
                </div>
              </div>
              <!-- Eto no atao ny page -->
              <div class="row">
                <div class="col-12">
                  <div class="card">
                    <div class="card-body">
                      <h4 class="mt-0 header-title">Nouvelle reference</h4>
                      <form action="/nouvelle_reference" method="post">
                        <div class="row">
                          <div class="col-xl-6">
                            <div class="form-group row">
                              <label
                                for="example-text-input"
                                class="col-sm-2 col-form-label"
                                >String</label
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
                                for="example-number-input"
                                class="col-sm-2 col-form-label"
                                >Entier</label
                              >
                              <div class="col-sm-10">
                                <input
                                  class="form-control"
                                  type="number"
                                  id="example-number-input"
                                  name="entier"
                                />
                              </div>
                            </div>
                            <div class="form-group row">
                              <label
                                for="example-number-input"
                                class="col-sm-2 col-form-label"
                                >Pas entier</label
                              >
                              <div class="col-sm-10">
                                <input
                                  class="form-control"
                                  type="text"
                                  id="example-number-input"
                                  name="pas_entier"
                                />
                              </div>
                            </div>
                            <div class="form-group row">
                              <label
                                for="example-datetime-local-input"
                                class="col-sm-2 col-form-label"
                                >Date et heure</label
                              >
                              <div class="col-sm-10">
                                <input
                                  class="form-control"
                                  type="datetime-local"
                                  id="example-datetime-local-input"
                                  name="date_heure"
                                />
                              </div>
                            </div>
                          </div>
                          <div class="col-xl-6">
                            <div class="form-group row">
                              <label class="col-sm-2 col-form-label"
                                >Select</label
                              >
                              <div class="col-sm-10">
                                <select class="form-control" name="id_option_reference">
                                  <%
                                  for (OptionReference optionReference : optionReferences) {
                                  %>
                                    <option value="<%= optionReference.getId() %>"><%= optionReference.getOption() %></option>
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
                                  name="date_simple"
                                />
                              </div>
                            </div>
                            <div class="form-group row">
                              <label
                                for="example-time-input"
                                class="col-sm-2 col-form-label"
                                >Heure</label
                              >
                              <div class="col-sm-10">
                                <input
                                  class="form-control"
                                  type="time"
                                  id="example-time-input"
                                  name="heure_simple"
                                />
                              </div>
                            </div>
                          </div>
                          <div class="col-xl-12">
                            <dt class="col-sm-3">Details reference <button style="margin-left: 30px;" type="button" class="btn btn-success" onclick="addNewLine()">+</button> </dt>
                            <div class="row m-t-20" id="append">
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
                <!-- end col -->
              </div>
              <div class="row">
                <div class="col-12">
                  <div class="card">
                    <div class="card-body">
                      <h4 class="mt-0 header-title">Liste des references</h4>
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
                            <th>String</th>
                            <th>Date Simple</th>
                            <th>Heure Simple</th>
                            <th>Date Heure</th>
                            <th>Entier</th>
                            <th>Pas Entier</th>
                            <th>Option</th>
                            <th></th>
                          </tr>
                        </thead>
                        <tbody>
                          <%
                          for (VReference vReference : vReferences) {
                          %>
                          <tr>
                            <td><%= vReference.getId() %></td>
                            <td><%= vReference.getString() %></td>
                            <td><%= vReference.getDateSimple() %></td>
                            <td><%= vReference.getHeureSimple() %></td>
                            <td><%= vReference.getDateHeure() %></td>
                            <td><%= vReference.getEntier() %></td>
                            <td><%= vReference.getPasEntier() %></td>
                            <td><%= vReference.getOption() %></td>
                            <td>
                              <div class="row">
                                <div class="col-4">
                                  <form action="/details_reference" method="get">
                                    <input type="hidden" name="id" value="<%= vReference.getId() %>">
                                    <button type="submit" class="btn btn-success">
                                      <i class="fas fa-info-circle"></i>
                                    </button>
                                  </form>
                                </div>
                                <div class="col-4">
                                  <form action="/update_reference" method="get">
                                    <input type="hidden" name="id" value="<%= vReference.getId() %>">
                                    <button type="submit" class="btn btn-primary">
                                      <i class="fas fa-pencil-alt"></i>
                                    </button>
                                  </form>
                                </div>
                                <div class="col-4">
                                  <form action="/delete_reference" method="post">
                                    <input type="hidden" name="id" value="<%= vReference.getId() %>">
                                    <button type="submit" class="btn btn-danger">
                                      <i class="fas fa-trash-alt"></i>
                                    </button>
                                  </form>
                                </div>
                              </div>
                            </td>
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
              <!-- Eto no atao ny page -->
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
              <div class="row">
                  <div class="col-5">
                      <div class="form-group row">
                          <label for="example-text-input" class="col-sm-2 col-form-label">Details</label>
                          <div class="col-sm-10">
                              <input class="form-control" type="text" id="example-text-input" name="details[]" />
                          </div>
                      </div>
                  </div>
                  <div class="col-5">
                      <div class="form-group row">
                          <label for="example-text-input" class="col-sm-2 col-form-label">Note</label>
                          <div class="col-sm-10">
                              <input class="form-control" type="text" id="example-text-input" name="note[]" />
                          </div>
                      </div>
                  </div>
                  <div class="col-2">
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
