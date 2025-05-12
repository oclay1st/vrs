export interface IRoutesNames {
    home: string;
    admin: string;
    login: string;
    auth: string;
    signup: string;
    userList: string;
    vehicleList: string;
    notFound: string;
}

const routesNames: Readonly<IRoutesNames> = {
    home: "home",
    admin: "admin",
    login: "login",
    auth: "auth",
    signup: "signup",
    userList: "userList",
    vehicleList: "vehicleList",
    notFound: "notFound",
}

export default routesNames;
