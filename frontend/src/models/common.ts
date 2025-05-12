export interface IPage<T> {
    page: number;
    pageSize: number;
    totalElements: number;
    totalPages: number;
    items: T[]
}
