import Home from './main-components/Home'
import LoginForm from './forms/LoginForm'
import RegistrationForm from './forms/RegistrationForm'
import Homepage from './main-components/Homepage'

import PregledCentara from './overviews/PregledCentara'
import SearchPage from './main-components/SearchPage'
import Upitnik from './forms/Upitnik'

import UserRegProfile from './main-components/UserRegProfile'
import UserRegupdateProfile from './main-components/UserRegupdateProfile'

import MedCentar from './overviews/MedCentar'
import Termini from './overviews/Termini'
import ZakazaniTerminiKorisnika from './overviews/ZakazaniTerminiKorisnika'
import EditCelandar from './forms/EditCalendar'
import CustomZakazivanje from './forms/customZakazivanje'

// import Mapa from './main-components/Mapa'
import Test from './main-components/Test'
// import Kamion from './main-components/Kamion'
import PrikazKretanja from './main-components/PrikazKretanja'
import Transport from './main-components/Transport'

import Narudzbine from './overviews/Narudzbine'
import Narucilac from './overviews/Narucilac'
export default[
    {
        path:`/medCentarOrders`,
        name:Narucilac,
        component:Narucilac
    },
    {
        path:`/orders`,
        name:Narudzbine,
        component:Narudzbine
    },
    {
        path:`/transport/:id`,
        name:Transport,
        component:Transport
    },
    {
        path:`/path/:id`,
        name:PrikazKretanja,
        component:PrikazKretanja
    },
    // {
    //     path:`/kamion`,
    //     name:'Kamion',
    //     component:Kamion
    // },
    // {
    //     path:`/mapa`,
    //     name:'Mapa',
    //     component:Mapa
    // },
    {
        path:`/test`,
        name:'Test',
        component:Test
    },
    {
        path:`/`,
        name:`Home`,
        component:Home
    },
    {
        path:`/login`,
        name:'LoginForm',
        component:LoginForm
    },
    {
        path:`/register`,
        name:'RegistrationForm',
        component:RegistrationForm
    },
    {
        path:`/home`,
        name:'Homepage',
        component:Homepage
    },
    {
        path:`/search`,
        name:'SearchPage',
        component:SearchPage
    },
    {
        path:`/hospitalsOverview`,
        name:"PregledCentara",
        component:PregledCentara
    },
    {
        path:`/upitnik`,
        name:"Upitnik",
        component:Upitnik
    },
    {
        path:'/profile/:id',
        name:'UserProfile',
        component:UserRegProfile
    },
    {
        path:'/profile/',
        name:'UserProfile',
        component:UserRegProfile
    },
    {
        path:'/profile/:id/update',
        name:'UserUpdateProfile',
        component:UserRegupdateProfile
    },
    {
        path:'/medcentar/:id',
        name:'MedCentar',
        component:MedCentar
    },
    {
        path:`/termini/:id`,
        name:'Termini',
        component:Termini
    },
    {
        path:`/bookedTermini`,
        name:'ZakazaniTerminiKorisnika',
        component:ZakazaniTerminiKorisnika
    },
    {
        path:`/updateTermini`,
        name:'ZakazaniTerminiKorisnika',
        component: EditCelandar
    },
    {
        path:`/customZakazi`,
        name:'customZakazivanje',
        component: CustomZakazivanje
    },
]
   