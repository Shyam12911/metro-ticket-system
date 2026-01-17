document.addEventListener("DOMContentLoaded", () => {
  const btn = document.getElementById("searchBtn");
  const originInput = document.getElementById("origin");
  const destinationInput = document.getElementById("destination");
  const resultsDiv = document.getElementById("results");

  btn.addEventListener("click", () => {
    const origin = originInput.value.trim();
    const destination = destinationInput.value.trim();

    if (!origin || !destination) {
      resultsDiv.innerHTML = "<p>Please enter origin and destination.</p>";
      return;
    }

    fetch(`/search?origin=${encodeURIComponent(origin)}&destination=${encodeURIComponent(destination)}`)
      .then(res => {
        if (!res.ok) throw new Error("No results");
        return res.json();
      })
      .then(data => {
        if (data.length === 0) {
          resultsDiv.innerHTML = "<p>No routes found.</p>";
          return;
        }

        resultsDiv.innerHTML = data.map(j => `
          <div class="result-card">
            <p><strong>${j.origin}</strong> → <strong>${j.destination}</strong></p>
            <p>Fare: ₹${j.fare}</p>
          </div>
        `).join("");
      })
      .catch(() => {
        resultsDiv.innerHTML = "<p>Error fetching data.</p>";
      });
  });
});
