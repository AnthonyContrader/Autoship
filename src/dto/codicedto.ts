import {CodiceStato} from './codicestato';
import { UserDTO } from './userdto';

export class CodiceDTO {
    
    id: number;

    otp: string;

    userId: number;

    stato: CodiceStato;

    cancellato: boolean;

}