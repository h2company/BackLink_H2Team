export class Action {
    constructor(
        public id?: number,
        public urlAction?: string,
        public username?: string,
        public keywords?: string[],
        public searchEngine?: string[],
        public userAgent?: string[],
        public point?: number,
        public blockPixel?: boolean,
        public filterVA?: boolean,
        public saveVA?: boolean,
        public beginTime?: number,
        public endTime?: number
    ) { }
}
