export class Backlink {
    constructor(
        public id?: number,
        public username?: string,
        public urlBacklink?: string,
        public point?: number,
        public limit?: number,
        public filterVA?: boolean,
        public saveVA?: boolean,
        public beginTime?: number,
        public endTime?: number
    ) { }
}

