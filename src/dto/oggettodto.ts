export class OggettoDTO {
    
    id: number;
    nome: string;
    dimensione: number;
    cancellato: boolean;
    cella: boolean;
    constructor(nome: string, dimensione: number) {
        this.nome = nome;
        this.dimensione = dimensione;
        this.cancellato = false;
        this.cella=false;
    }
}
