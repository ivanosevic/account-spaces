document.addEventListener('DOMContentLoaded', new function () {
  const summaryProfileTextbox = document.getElementById('summaryProfile');
  M.CharacterCounter.init(summaryProfileTextbox);

  const modals = document.querySelectorAll('.modal');
  M.Modal.init(modals, {});
});