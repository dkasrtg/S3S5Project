// Function to check if the "error" parameter exists in the URL
function checkErrorParameter() {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('error')) {
      const errorValue = urlParams.get('error');
      alert(errorValue);
      urlParams.delete('error');
      const updatedUrl = `${window.location.origin}${window.location.pathname}?${urlParams.toString()}`;
      window.history.replaceState({}, document.title, updatedUrl);
    }
  }
  
  // Wait for the page to finish loading before executing the function
  window.addEventListener('load', function() {
    checkErrorParameter();
  });