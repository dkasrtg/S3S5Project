<%@ page isErrorPage="true" %>
<%@ page import="entity.meuble.*" %>
<%@ page import="entity.materiau.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui"
    />
    <title>Meuble - Fabrication</title>
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
                        <li class="breadcrumb-item active">Fabrication</li>
                      </ol>
                    </div>
                    <h4 class="page-title">Fabrication</h4>
                  </div>
                </div>
              </div>
              <!-- Eto no atao ny page -->
              <div class="row">
                <div class="col-12">
                  <div class="card">
                    <div class="card-body">
											<div class="row">
												<div class="col-10">
													<h4 class="mt-0 header-title">Verification fabrication meuble</h4>
												</div>
												<div class="col-1">
													<form action="/fabrication_meuble" method="get">
														<button
															type="submit"
															class="btn btn-danger waves-effect waves-light"
														>
															Annuler
														</button>
													</form>
												</div>
												<div class="col-1">
													<button
														type="submit"
														class="btn btn-primary waves-effect waves-light"
													>
														Valider
													</button>
												</div>
											</div>
                      <div class="row">
                        <div class="col-6">
                          <div class="table-responsive">
                            <table class="table mb-0 table-centered">
                              <tbody>
                                <tr>
                                  <td>Meuble:</td>
                                  <td></td>
                                </tr>
                                <tr>
                                  <td>Date:</td>
                                  <td></td>
                                </tr>
                                <tr>
                                  <td>Quantite:</td>
                                  <td></td>
                                </tr>
                                <tr>
                                  <td>Marge Beneficiaire:</td>
                                  <td>
                                    
                                  </td>
                                </tr>
                                <tr>
                                  <td>Cout unitaire de fabrication:</td>
                                  <td></td>
                                </tr>
                                <tr>
                                  <td>Cout total de fabrication:</td>
                                  <td></td>
                                </tr>
                                <tr>
                                  <td>Prix unitaire de vente:</td>
                                  <td></td>
                                </tr>
                                <tr>
                                  <td>Composant</td>
                                  <td></td>
                                </tr>
                              </tbody>
                            </table>
                          </div>
                        </div>
                        <div class="col-12">
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
															<th>Type de materiau</th>
															<th>Materiau</th>
															<th>Unite</th>
															<th>Dimension</th>
															<th>Quantite requis</th>
															<th>Prix unitaire</th>
														</tr>
													</thead>
													<tbody>
																												
													</tbody>
                        </table>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- Eto no atao ny page -->
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
