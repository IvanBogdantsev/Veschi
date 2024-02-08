//
//  DepsInjector.swift
//  iosApp
//
//  Created by Ilya Saushin on 07.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

func startKoin() {

    let koinApplication = Koin_iosKt.doInitKoiniOS()
    _koin = koinApplication.koin
    koin.openKoinScope()
}

private var _koin: Koin_coreKoin?
var koin: Koin_coreKoin {
    return _koin!
}

let Di = DepsInjector.instance

class DepsInjector {
 
    /** Singletion */
    fileprivate  static let instance = DepsInjector()

    private init() {}
    
    func getAuthUiStatePublisher() -> AuthStatePublisheriOS {
        guard let publisher = koin.getFromScope(objCClass: AuthStatePublisheriOS.self) as? AuthStatePublisheriOS else {
            fatalError("AuthStatePublisheriOS cannot be null")
        }
        return publisher
    }
}
