export interface Producto {
    nombre:           string;
    precio:           number;
    valor_energetico: string;
    categoria:        Categoria;
    imagen_url:       string;
}

export enum Categoria {
    Carne = "carne",
    Lácteo = "lácteo",
    Pescado = "pescado",
    Vegetal = "vegetal",
}
export interface pedido {
    id:        number;
    nombre:    string;
    productos: Producto[];
    precio:    number;
}