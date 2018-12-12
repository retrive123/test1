export interface ISecretKey {
    id?: number;
    uniqueId?: number;
    manuId?: number;
    assignmentStatus?: boolean;
    validStatus?: boolean;
}

export class SecretKey implements ISecretKey {
    constructor(
        public id?: number,
        public uniqueId?: number,
        public manuId?: number,
        public assignmentStatus?: boolean,
        public validStatus?: boolean
    ) {
        this.assignmentStatus = this.assignmentStatus || false;
        this.validStatus = this.validStatus || false;
    }
}
