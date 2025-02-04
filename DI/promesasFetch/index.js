let objeto;
fetch("https://dummyjson.com/products")
  .then((response) => {
    return response.json();
  })
  .then((data) => {
    id = 0;
    data.products.forEach((product) => {
      objeto = data;
      let carta = document.querySelector("#taskContainer");
      carta.innerHTML += `<div class="card " style="width: 18rem;">
  <div class="card-body">
  <img src="${product.images}" class="card-img-top" alt="...">
    <h5 class="card-title">${product.title}</h5>
    <p class="card-text">${product.description}</p>
    <a href="#" class="btn btn-primary detalles-btn" data-product='${JSON.stringify(
      product
    )}'>Descripcion</a>
  </div>
</div>`;
      id++;
    });

    // Add event listeners to all "Detalles" buttons
    document.querySelectorAll(".detalles-btn").forEach((button) => {
      button.addEventListener("click", (event) => {
        const product = JSON.parse(event.target.getAttribute("data-product"));
        verDescription(product);
      });
    });
  })
  .catch((error) => {
    console.error("Error fetching products:", error);
  });

function verDescription(product) {
  alert(
    `Title: ${product.title}\nDescription: ${product.description}\nPrice: ${product.price}`
  );
}
