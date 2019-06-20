import * as React from 'react';
import {GoLink} from '@src/store/GoLinkService';
import {observer} from 'mobx-react';

interface Props {
  goLink: GoLink,
  onDelete: () => void;
}

export const GoLinkItem: React.FunctionComponent<Props> = observer((props: Props) => {
  return (
      <li>
        <b>go/{props.goLink.alias}:</b>
        {' '}<a href={props.goLink.url}>{props.goLink.url}</a>
        {' '}({props.goLink.createdString})
        {' '}[<span className="clickable" onClick={props.onDelete}>delete</span>]
      </li>
  );
});
