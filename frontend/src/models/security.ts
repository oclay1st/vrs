
export interface ILoginDTO {
    username: string
    password: string
}

export interface ITokenDTO {
    accessToken: string
    refreshToken: string
    tokenLifeTime: string
    refreshTokenExpirationTime: string
}

export interface IUserProfile {
    id: number
    username: string
    name: string
    lastName: string
}

export interface IUser {
    id: number
    username: string
    name: string
    lastName: string
}

