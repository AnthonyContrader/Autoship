import { OggettoDTO } from './oggettodto';
import { CodiceDTO } from './codicedto';


export class MagazzinoDTO {

    id: number;

    oggettoId: number;

    oggettoNome: string;

    capienza: number;

    codiceId: number;

    codiceOtp: string;

    cancellato: boolean;

}