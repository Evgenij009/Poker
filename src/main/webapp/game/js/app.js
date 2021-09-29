class Socket {
    constructor() {
        this.ws = new WebSocket(`ws://${location.host}/game-poker`);
        this.ws.onopen = this.onOpenSocket;
        this.ws.onmessage = (e) => this.onMessage(JSON.parse(e.data));
        this.ws.onclose = this.onClose;
        this.events = {};
    }

    emit(event, data, callback) {
        this.ws.send(JSON.stringify({
            event: event,
            data: data
        }));
        if (callback !== undefined) {
            this.on(event, callback);
        }
    }

    onOpenSocket() {

    }

    onClose() {

    }

    removeAllListeners() {
        this.events.clear();
    }

    on(event, callback) {
        this.events[event] = callback;
    }

    onMessage(msg) {
        this.events[msg.event](msg.data);
    }
}

const socket = new Socket();

var app = angular.module('app', ['ngRoute']).config(function ($routeProvider, $locationProvider) {
    $routeProvider.when('/table-10/:tableId', {
        templateUrl: '/game/partials/table-10-handed.html',
        controller: 'TableController',
    });

    $routeProvider.when('/table-6/:tableId', {
        templateUrl: '/game/partials/table-6-handed.html',
        controller: 'TableController',
    });

    $routeProvider.when('/table-2/:tableId', {
        templateUrl: '/game/partials/table-2-handed.html',
        controller: 'TableController',
    });

    $routeProvider.when('/', {
        templateUrl: '/game/partials/lobby.html',
        controller: 'LobbyController',
    });

    $routeProvider.otherwise({redirectTo: '/'});

    $locationProvider.html5Mode(true).hashPrefix('!');
});

app.run(function ($rootScope) {
    console.log($rootScope);
    $rootScope.screenName = '';
    $rootScope.totalChips = 0;
    $rootScope.sittingOnTable = '';
});