<%@ page isErrorPage="true" %>
<%@ page import="entity.reference.OptionReference" %>
<%@ page import="entity.reference.VReference" %>
<%@ page import="entity.reference.Checkbox" %>
<%@ page import="entity.reference.VCheckboxReference" %>
<%@ page import="java.util.List" %>
<%
List<OptionReference> optionReferences = (List<OptionReference>) request.getAttribute("optionReferences");
List<OptionReference> optionReferencesRadio = (List<OptionReference>) request.getAttribute("optionReferencesRadio");
List<VCheckboxReference> vCheckboxReferences = (List<VCheckboxReference>) request.getAttribute("vCheckboxReferences");
List<Checkbox> checkboxs = (List<Checkbox>) request.getAttribute("checkboxs");
VReference vReference = (VReference) request.getAttribute("vReference");
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
    <title>Update Reference</title>
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
                      <h4 class="mt-0 header-title">Update reference <%= vReference.getId() %></h4>
                      <form action="/update_reference" method="post">
                        <input type="hidden" name="id" value="<%= vReference.getId() %>">
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
                                  value="<%= vReference.getString() %>"
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
                                  value="<%= vReference.getEntier() %>"
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
                                  value="<%= vReference.getPasEntier() %>"
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
                                  value="<%= vReference.getDateHeure() %>"
                                />
                              </div>
                            </div>
                            <div class="form-group row">
                              <label class="col-md-3 my-1 control-label"
                                >Radios</label
                              >
                              <div class="col-md-9">
                                <div class="form-check-inline my-1">
                                  <div class="custom-control custom-radio">
                                    <input
                                      type="radio"
                                      id="customRadio0"
                                      name="id_radio_reference"
                                      class="custom-control-input"
                                      value="<%= vReference.getIdRadioReference() %>"
                                      checked
                                    />
                                    <label
                                      class="custom-control-label"
                                      for="customRadio0"
                                      ><%= vReference.getRadio() %></label
                                    >
                                  </div>
                                </div>
                                <%
                                  int j=1;  
                                  for (OptionReference optionReferenceRadio : optionReferencesRadio) {
                                  %>
                                    <div class="form-check-inline my-1">
                                      <div class="custom-control custom-radio">
                                        <input
                                          type="radio"
                                          id="customRadio<%= j %>"
                                          name="id_radio_reference"
                                          class="custom-control-input"
                                          value="<%= optionReferenceRadio.getId() %>"
                                        />
                                        <label
                                          class="custom-control-label"
                                          for="customRadio<%= j %>"
                                          ><%= optionReferenceRadio.getOption() %></label
                                        >
                                      </div>
                                    </div>
                                  <%
                                  j++;
                                  }
                                  %>
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
                                  <option value="<%= vReference.getIdOptionReference() %>"><%= vReference.getOption() %></option>
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
                                  value="<%= vReference.getDateSimple() %>"
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
                                  value="<%= vReference.getHeureSimple() %>"
                                />
                              </div>
                            </div>
                            <div class="form-group row">
                              <label class="col-md-3 my-2 control-label"
                                >Checkboxs</label
                              >
                              <div class="col-md-9">
                                <%
                                  int i = 0;
                                  for (VCheckboxReference vCheckboxReference : vCheckboxReferences) {
                                  %>
                                  <div class="checkbox my-2">
                                    <div class="custom-control custom-checkbox">
                                      <input
                                        type="checkbox"
                                        class="custom-control-input"
                                        id="customCheck<%= i %>"
                                        data-parsley-multiple="groups"
                                        name="id_checkbox_reference[]"
                                        value="<%= vCheckboxReference.getIdCheckbox() %>"
                                        checked
                                      />
                                      <label
                                        class="custom-control-label"
                                        for="customCheck<%= i %>"
                                        ><%= vCheckboxReference.getNom() %></label
                                      >
                                    </div>
                                  </div>
                                  <%
                                  i++;
                                  }
                                  for (Checkbox checkbox : checkboxs) {
                                    %>
                                    <div class="checkbox my-2">
                                      <div class="custom-control custom-checkbox">
                                        <input
                                          type="checkbox"
                                          class="custom-control-input"
                                          id="customCheck<%= i %>"
                                          data-parsley-multiple="groups"
                                          name="id_checkbox_reference[]"
                                          value="<%= checkbox.getId() %>"
                                        />
                                        <label
                                          class="custom-control-label"
                                          for="customCheck<%= i %>"
                                          ><%= checkbox.getNom() %></label
                                        >
                                      </div>
                                    </div>
                                    <%
                                    i++;
                                    }
                                  %>
                                  
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
                <!-- end col -->
              </div>
              <!-- Eto no atao ny page -->
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
