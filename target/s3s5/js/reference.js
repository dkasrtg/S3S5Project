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