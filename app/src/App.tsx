import * as React from 'react';
import * as ReactDOM from 'react-dom';

import 'main.css';
import {GoLinkList} from '@src/components/GoLinkList';
import {AddGoLink} from '@src/components/AddGoLink';
import {GoLinkStore} from '@src/store/goLinkStore';
import {MessageBar} from '@src/components/MessageBar';

const goLinkStore = new GoLinkStore();
goLinkStore.fetchGoLinks();

const app: React.ReactElement<any> = (
    <div>
      <AddGoLink store={goLinkStore}/>
      <MessageBar store={goLinkStore}/>
      <GoLinkList store={goLinkStore}/>
    </div>
);

ReactDOM.render(app, document.getElementById('app'));
