import * as React from 'react';

interface Props {
}

interface State {
  aliasInput: string,
  urlInput: string,
}

const INITIAL_STATE: State = {
  aliasInput: '',
  urlInput: '',
};

export class AddGoLink extends React.Component<Props, State> {

  readonly state: State = INITIAL_STATE;

  constructor(props: Props) {
    super(props);
    this.onAddButtonClick = this.onAddButtonClick.bind(this);
    this.onInputChange = this.onInputChange.bind(this);
    this.hasInput = this.hasInput.bind(this);
  }

  onAddButtonClick(event: React.MouseEvent<HTMLButtonElement>) {
    event.preventDefault();
    console.log(this.state);
  }

  onInputChange(event: React.ChangeEvent<HTMLInputElement>) {
    const targetName = event.target.name;
    const value = event.target.value;
    // @ts-ignore
    this.setState({[targetName]: value});
  }

  hasInput(): boolean {
    return this.state.urlInput.length > 0 && this.state.aliasInput.length > 0;
  }

  render() {
    return (
        <div>
          <input
              name="aliasInput"
              value={this.state.aliasInput}
              onChange={this.onInputChange}
              type="text"
              placeholder="Enter an alias"
          />
          <input
              name="urlInput"
              value={this.state.urlInput}
              onChange={this.onInputChange}
              type="text"
              placeholder="Enter a url"
          />
          <button onClick={this.onAddButtonClick} disabled={!this.hasInput()}>Add</button>
        </div>
    );
  }
}
