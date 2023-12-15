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
                      <h4 class="mt-0 header-title">Dimension et nombre des materiaux</h4>
                      <form action="#" method="post">
                        <div class="row">
                          <div class="col-xl-12">
                            <div class="row">
                              <div class="col-1">
                                <div class="form-group row">
                                  <div class="col-md-9">
                                      <div class="checkbox my-2">
                                        <div class="custom-control custom-checkbox">
                                          <input
                                            type="checkbox"
                                            class="custom-control-input"
                                            id="c1"
                                            data-parsley-multiple="groups"
                                            name="id_checkbox_reference[]"
                                            value=""
                                          />
                                          <label
                                            class="custom-control-label"
                                            for="c1"
                                            > </label
                                          >
                                        </div>
                                      </div>
                                  </div>
                                </div>
                              </div>
                              <div class="col-2">
                                <div class="form-group row">
                                  <label
                                    for="example-text-input"
                                    class="col-sm-4 col-form-label"
                                    >Type</label
                                  >
                                  <div class="col-sm-8">
                                    <input
                                      class="form-control"
                                      type="text"
                                      id="example-text-input"
                                      name="string"
                                      value="Bois"
                                    />
                                  </div>
                                </div>
                              </div>
                              <div class="col-2">
                                <div class="form-group row">
                                  <label
                                    for="example-text-input"
                                    class="col-sm-4 col-form-label"
                                    >Nom</label
                                  >
                                  <div class="col-sm-8">
                                    <input
                                      class="form-control"
                                      type="text"
                                      id="example-text-input"
                                      name="string"
                                      value="Palissandre"
                                    />
                                  </div>
                                </div>
                              </div>
                              <div class="col-3">
                                <div class="form-group row">
                                  <label class="col-sm-4 col-form-label"
                                    >Dimension</label
                                  >
                                  <div class="col-sm-8">
                                    <select class="form-control" name="id_option_reference">
                                      
                                    </select>
                                  </div>
                                </div>
                              </div>
                              <div class="col-3">
                                <div class="form-group row">
                                  <label
                                    for="example-text-input"
                                    class="col-sm-4 col-form-label"
                                    >Quantite</label
                                  >
                                  <div class="col-sm-8">
                                    <input
                                      class="form-control"
                                      type="text"
                                      id="example-text-input"
                                      name="string"
                                      value=""
                                    />
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-10"></div>
                              <div class="col-1">
                                <button
                                    type="submit"
                                    class="btn btn-danger waves-effect waves-light"
                                  >
                                    Precedent
                                  </button>
                              </div>
                              <div class="col-1">
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
