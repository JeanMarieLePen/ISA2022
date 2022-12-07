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

export default[
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
    }
]
   