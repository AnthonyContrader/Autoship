import { OggettoDTO } from './oggettodto';
import { CodiceDTO } from './codicedto';


export class MagazzinoDTO {

    id: number;

    oggetto: OggettoDTO;

    capienza: number;

    codice: CodiceDTO;

    cancellato: boolean;

}