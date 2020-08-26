import {CodiceStato} from './codicestato';
import { UserDTO } from './userdto';

export class CodiceDTO {
    
    id: number;

    otp: string;

    user: UserDTO;

    stato: CodiceStato;

    cancellato: boolean;

}